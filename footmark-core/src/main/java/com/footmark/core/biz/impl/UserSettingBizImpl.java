package com.footmark.core.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footmark.common.enums.ExceptionEnum;
import com.footmark.common.exception.BusinessException;
import com.footmark.core.biz.UserSettingBiz;
import com.footmark.core.dao.impl.UserSettingDaoImpl;
import com.footmark.core.entity.UserInfo;
import com.footmark.core.entity.UserSetting;
import com.footmark.core.enums.AccepterCategoryEnum;
import com.footmark.core.service.UserInfoService;
import com.footmark.core.service.UserSettingService;
import com.footmark.core.vo.SettingVo;

/**
 * @Description: 用户设置实现
 * @author carlos.chu
 * @version 1.0 2015年12月16日 下午3:04:01
 */
@Service
public class UserSettingBizImpl implements UserSettingBiz {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserSettingService userSettingService;
    @Autowired
    private UserSettingDaoImpl userSettingDao;

    /**
     * 用户设置，add or update
     */
    @Override
    public void setSetting(UserSetting userSetting) {
        UserInfo userInfo = userInfoService.queryByUserId(userSetting.getUserId());
        if (userInfo == null) {
            throw new BusinessException(ExceptionEnum.USER_NOT_FOUND_ERR);
        }
        UserSetting exitSetting = userSettingService.query(userInfo.getId(), userSetting.getKey().getDomain());
        if (exitSetting == null) {
            userSettingDao.add(userSetting);
        } else {
            userSetting.setKey(userSetting.getKey());
            userSettingDao.update(userSetting);
        }
    }

    @Override
    public List<SettingVo> queryByUid(Long userId) {
        List<UserSetting> settings = userSettingService.query(userId);
        List<SettingVo> vos = new ArrayList<SettingVo>();
        for (UserSetting us : settings) {
            SettingVo settingVo = new SettingVo();
            settingVo.setDomain(us.getDomain());
            if (UserSetting.SCOPE.equals(us.getDomain())) {
                settingVo.setValue(us.getValue() + "m");
            } else if (UserSetting.GENDER_CATEGORY.equals(us.getDomain())) {
                settingVo.setValue(AccepterCategoryEnum.toEnum(us.getValue()).getDesc());
            }
            vos.add(settingVo);
        }
        return vos;
    }
}
