package com.footmark.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footmark.core.dao.impl.UserSettingDaoImpl;
import com.footmark.core.entity.UserSetting;
import com.footmark.core.enums.UserSettingEnum;
import com.footmark.core.service.UserSettingService;

@Service
public class UserSettingServiceImpl implements UserSettingService {

    private static final String QUERY_BY_UID = "queryByUid";
    private static final String QUERY_BY_UID_KEY = "queryByUidAndKey";
    private static final String QUERY_BY_UID_DOMAIN = "queryByUidAndDomain";

    @Autowired
    private UserSettingDaoImpl userSettingDao;

    @SuppressWarnings("unchecked")
    @Override
    public List<UserSetting> query(Long userId) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userId", userId);
        return userSettingDao.query(QUERY_BY_UID, paramsMap);
    }

    @Override
    public List<UserSetting> populateDefaultSettings(Long userId) {
        List<UserSetting> userSettings = new ArrayList<>();
        UserSetting acceptSetting = new UserSetting(userId, UserSettingEnum.ACCEPT_CATEGORY_ALL);
        UserSetting scopeSetting = new UserSetting(userId, UserSettingEnum.ACCEPT_SCOPE_500);
        userSettings.add(scopeSetting);
        userSettings.add(acceptSetting);
        return userSettings;
    }

    @Override
    public void addDefaultSettings(Long userId) {
        for (UserSetting userSetting : populateDefaultSettings(userId)) {
            userSettingDao.add(userSetting);
        }
    }

    @Override
    public UserSetting query(Long userId, UserSettingEnum key) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userId", userId);
        paramsMap.put("key", key);
        return (UserSetting) userSettingDao.queryOne(QUERY_BY_UID_KEY, paramsMap);
    }

    @Override
    public UserSetting query(Long userId, String domain) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        paramsMap.put("userId", userId);
        paramsMap.put("domain", domain);
        return (UserSetting) userSettingDao.queryOne(QUERY_BY_UID_DOMAIN, paramsMap);
    }

}
