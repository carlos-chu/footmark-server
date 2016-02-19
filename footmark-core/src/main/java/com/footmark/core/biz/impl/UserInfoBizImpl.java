package com.footmark.core.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.footmark.common.enums.ExceptionEnum;
import com.footmark.common.exception.BusinessException;
import com.footmark.common.util.StringUtil;
import com.footmark.common.util.SystemUtil;
import com.footmark.core.biz.UserInfoBiz;
import com.footmark.core.dao.impl.UserInfoDaoImpl;
import com.footmark.core.entity.UserFriend;
import com.footmark.core.entity.UserInfo;
import com.footmark.core.enums.RelationAcceptStatusEnum;
import com.footmark.core.enums.RelationTypeEnum;
import com.footmark.core.enums.UserLevelEnum;
import com.footmark.core.service.UserFriendService;
import com.footmark.core.service.UserInfoService;
import com.footmark.core.service.UserSettingService;
import com.footmark.core.vo.FriendInfoVo;

/**
 * @Description: 用户业务实现
 * @author carlos.chu
 * @date 2015年8月20日
 */
@Service
public class UserInfoBizImpl implements UserInfoBiz {

    @Autowired
    UserInfoService userInfoService;
    @Autowired
    UserInfoDaoImpl userInfoDao;
    @Autowired
    UserSettingService userSettingService;
    @Autowired
    UserFriendService userFriendService;

    @Override
    public Void validateTelNo(String mobileNo) {
        UserInfo user = userInfoService.queryByMobileNo(mobileNo);
        if (!StringUtil.isEmpty(user)) {
            throw new BusinessException(ExceptionEnum.MOBILENO_EXITED_ERR);
        }
        // 1.下发短信验证码

        return null;
    }

    @Override
    public Void validateIdentifyingCode(String mobileNo, String telCode) {
        // 1.校验短信验证码

        return null;
    }

    @Override
    public UserInfo updateUserInfo(UserInfo user) {
        userInfoDao.update(user);
        return user;
    }

    @Override
    @Transactional
    public Long register(UserInfo userInfo) {
        UserInfo idempotentUser = userInfoService.queryByMobileNo(userInfo.getTelNum());
        if (idempotentUser != null) {
            throw new BusinessException(ExceptionEnum.MOBILENO_EXITED_ERR);
        }
        String password = userInfo.getPassword();
        userInfo.setPassword(SystemUtil.getMD5Str(password));
        // 注册用户级别是完善基本信息
        userInfo.setLevel(UserLevelEnum.IMPROVE_INFO);
        userInfoService.addUser(userInfo);
        userSettingService.addDefaultSettings(userInfo.getId());
        return userInfo.getId();
    }

    @Override
    public UserInfo login(UserInfo user) {
        String password = SystemUtil.getMD5Str(user.getPassword());
        UserInfo userInfo = userInfoService.queryByMobileNoAndPsw(user.getTelNum(), password);
        if (userInfo == null) {
            throw new BusinessException(ExceptionEnum.USER_LOGIN_ERR);
        }
        return userInfo;
    }

    @Override
    public FriendInfoVo queryFriendById(Long userId, Long friendId) {
        FriendInfoVo vo = null;
        UserInfo friend = userInfoService.queryByUserId(friendId);
        if (friend != null) {
            vo = new FriendInfoVo();
            vo.setGender(friend.getGender().getCode());
            vo.setAvatar(friend.getAvatar());
            vo.setId(friendId);
            vo.setName(friend.getName());
            vo.setRelationStatus(RelationAcceptStatusEnum.UNDO.getCode());
            vo.setRelationType(RelationTypeEnum.ACTIVE.getCode());
            UserFriend userFriend = userFriendService.queryByUidAndFid(userId, friendId);
            if (userFriend != null) {
                vo.setRelationStatus(userFriend.getAcceptStatus().getCode());
                vo.setRelationType(userFriend.getRelationType().getCode());
            }
        }
        return vo;
    }
}
