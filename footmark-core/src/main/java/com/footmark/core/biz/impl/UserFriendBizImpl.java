package com.footmark.core.biz.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONObject;
import com.footmark.common.enums.ExceptionEnum;
import com.footmark.common.enums.StatusEnum;
import com.footmark.common.exception.BusinessException;
import com.footmark.common.util.SortUtil;
import com.footmark.core.biz.UserFriendBiz;
import com.footmark.core.dao.impl.UserFriendDaoImpl;
import com.footmark.core.entity.PushInfo;
import com.footmark.core.entity.UserFriend;
import com.footmark.core.entity.UserInfo;
import com.footmark.core.enums.PromptTypeEnum;
import com.footmark.core.enums.RelationAcceptStatusEnum;
import com.footmark.core.enums.RelationTypeEnum;
import com.footmark.core.service.PushInfoService;
import com.footmark.core.service.UserFriendService;
import com.footmark.core.service.UserInfoService;
import com.footmark.core.vo.FriendInfoVo;
import com.footmark.gateway.push.AndroidPushMsg;
import com.footmark.gateway.push.PushConstant;
import com.footmark.gateway.push.entity.PushMessage;

/**
 * @Description: 用户好友业务实现
 * @author carlos.chu
 * @date 2015年9月11日
 */
@Service
public class UserFriendBizImpl implements UserFriendBiz {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserFriendDaoImpl userFriendDao;
    @Autowired
    private UserFriendService userFriendService;
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PushInfoService pushInfoService;
    @Autowired
    private AndroidPushMsg androidPushMsg;

    /**
     * 此时创建两条记录，主动申请的和被加好友的，关系不同
     * 
     * @param UserFriend
     */
    @Transactional
    public boolean apply(UserFriend userFriend) {
        UserInfo userInfo = userInfoService.queryByUserId(userFriend.getUserId());
        UserInfo friendInfo = userInfoService.queryByUserId(userFriend.getFriendId());
        if (userInfo == null || friendInfo == null) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND_ERR);
        }
        UserFriend exitfriend = userFriendService.queryByUidAndFid(userFriend.getUserId(), userFriend.getFriendId());
        if (exitfriend != null) {
            return false;
        }
        checkBlack(userFriend);
        // 主动方加好友
        userFriend.setAcceptStatus(RelationAcceptStatusEnum.UNACCEPT);
        userFriend.setRelationType(RelationTypeEnum.ACTIVE);
        userFriendDao.add(userFriend);
        // 被动方添加好友
        UserFriend passiveFriend = new UserFriend();
        passiveFriend.setUserId(userFriend.getFriendId());
        passiveFriend.setFriendId(userFriend.getUserId());
        passiveFriend.setAcceptStatus(RelationAcceptStatusEnum.UNACCEPT);
        passiveFriend.setRelationType(RelationTypeEnum.PASSIVE);
        userFriendDao.add(passiveFriend);
        return true;
    }

    /**
     * 检查黑名单过滤器
     * 
     * @param userFriend
     */
    private void checkBlack(UserFriend userFriend) {

    }

    @Transactional
    public boolean agree(UserFriend userFriend) {
        UserFriend exitfriend = userFriendService.queryByUidAndFid(userFriend.getUserId(), userFriend.getFriendId());
        if (exitfriend == null) {
            throw new BusinessException(ExceptionEnum.USER_FRIEND_NOT_FOUND_ERR);
        }
        // 1.更新原始好友关系为已接受
        int record = userFriendService.updateUserFriendAcceptStatus(exitfriend.getId(),
                RelationAcceptStatusEnum.ACCEPTED);
        if (record != 1) {
            throw new BusinessException(ExceptionEnum.UPDATE_DATA_ERR);
        }
        // 2.更新被动好友关系为已接受
        UserFriend passiveUserFriend = userFriendService.queryByUidAndFid(userFriend.getFriendId(),
                userFriend.getUserId());
        int record2 = userFriendService.updateUserFriendAcceptStatus(passiveUserFriend.getId(),
                RelationAcceptStatusEnum.ACCEPTED);
        if (record2 != 1) {
            throw new BusinessException(ExceptionEnum.UPDATE_DATA_ERR);
        }
        return true;
    }

    @Transactional
    public void refuse(UserFriend userFriend) {
        // 1.更新原始好友关系为拒绝
        int record = userFriendService.updateUserFriendAcceptStatus(userFriend.getFriendId(),
                RelationAcceptStatusEnum.REFUSE);
        if (record != 1) {
            throw new BusinessException(ExceptionEnum.UPDATE_DATA_ERR);
        }
        // 2.更新被动好友关系为拒绝
        UserFriend passiveUserFriend = userFriendDao.get(userFriend.getUserId());
        int record2 = userFriendService.updateUserFriendAcceptStatus(passiveUserFriend.getFriendId(),
                passiveUserFriend.getUserId(), RelationAcceptStatusEnum.REFUSE);
        if (record2 != 1) {
            throw new BusinessException(ExceptionEnum.UPDATE_DATA_ERR);
        }
    }

    @Override
    public void delete(Long userFriendId) {
        UserFriend userFriend = new UserFriend();
        userFriend.setId(userFriendId);
        userFriend.setStatus(StatusEnum.DEL);
        userFriendDao.update(userFriend);
    }

    @Override
    public void addToBlackList(UserFriend userFriend) {
        // 1.原始好友关系更新关系类型
        userFriendService.updateUserFriendRelationType(userFriend.getUserId(), userFriend.getFriendId(),
                RelationTypeEnum.ACTIVE_BLACKLIST, StatusEnum.DEL);
        // 2.被动好友关系更新关系类型
        userFriendService.updateUserFriendRelationType(userFriend.getFriendId(), userFriend.getUserId(),
                RelationTypeEnum.PASSIVE_BLACKLIST, StatusEnum.DEL);
    }

    @Override
    public List<UserFriend> queryByUserId(Long userId, RelationAcceptStatusEnum acceptStatus) {
        return userFriendService.queryByAcceptStatus(userId, acceptStatus);
    }

    @Override
    public Map<String, Object> queryFriendInfoByUserIdSortByName(Long userId, RelationAcceptStatusEnum acceptStatus) {
        List<UserFriend> friends = null;
        if (acceptStatus == null) {
            friends = userFriendService.queryByUid(userId);
        } else {
            friends = queryByUserId(userId, acceptStatus);
        }
        List<Long> friendIds = new ArrayList<Long>();
        for (UserFriend uf : friends) {
            friendIds.add(uf.getFriendId());
        }
        List<UserInfo> friendInfos = userInfoService.queryByIds(friendIds);
        return buildResultByName(friendInfos);
    }

    private Map<String, Object> buildResultByName(List<UserInfo> friendInfos) {
        List<FriendInfoVo> vos = new ArrayList<FriendInfoVo>();
        for (UserInfo userInfo : friendInfos) {
            FriendInfoVo userInfoVo = new FriendInfoVo();
            userInfoVo.setId(userInfo.getId());
            userInfoVo.setName(userInfo.getName());
            userInfoVo.setAvatar(userInfo.getAvatar());
            userInfoVo.setGender(userInfo.getGender().getCode());
            vos.add(userInfoVo);
        }
        // 对好友进行字母排序
        SortUtil sort = new SortUtil();
        return sort.sort(vos);
    }

    @Override
    public List<FriendInfoVo> queryFriendInfoByUserId(Long userId, RelationAcceptStatusEnum acceptStatus) {
        return userFriendService.queryFriendByUid(userId, acceptStatus);
    }

    public void pushMsg(UserFriend userFriend, PromptTypeEnum promptTypeEnum) {
        PushInfo pushInfo = pushInfoService.queryByUid(userFriend.getFriendId());
        if (pushInfo != null) {
            logger.info("推送消息开始，userFriend:{}", userFriend);
            try {
                PushMessage message = new PushMessage();
                JSONObject json = new JSONObject();
                json.put(PushConstant.FROM_UID, userFriend.getUserId());
                json.put(PushConstant.PROMPT_TYPE, promptTypeEnum.getCode());
                message.setMessage(json.toJSONString());
                androidPushMsg.pushToSingleDevice(AndroidPushMsg.MessageType.PASS_MESSAGE.code,
                        pushInfo.getBdChannelId(), message);
            } catch (Exception e) {
                logger.error("推送消息失败，promptTypeEnum:{}", promptTypeEnum, e);
            }
        }
    }

    public static void main(String[] args) {
        AndroidPushMsg push = new AndroidPushMsg();
        PushMessage message = new PushMessage();
        message.setMessage("test");
        try {
            push.pushToSingleDevice(0, "4255617592594596739", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
