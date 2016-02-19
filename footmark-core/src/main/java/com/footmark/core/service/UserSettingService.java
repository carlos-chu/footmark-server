package com.footmark.core.service;

import java.util.List;

import com.footmark.core.entity.UserSetting;
import com.footmark.core.enums.UserSettingEnum;

/**
 * @Description: 用户设置服务
 * @author carlos.chu
 * @date 2015年8月20日
 */
public interface UserSettingService {

    /**
     * 根据用户的id获取用户设置
     * 
     * @param userId
     * @return List<UserSetting>
     */
    List<UserSetting> query(Long userId);

    /**
     * 组装用户默认设置
     * 
     * @param userId
     * @return
     */
    List<UserSetting> populateDefaultSettings(Long userId);

    /**
     * 添加默认用户设置
     * 
     * @param userId
     */
    void addDefaultSettings(Long userId);

    /**
     * 通过uid和key查询用户设置
     * 
     * @param userId
     * @param key
     * @return
     */
    UserSetting query(Long userId, UserSettingEnum key);
    
    /**
     * 通过uid和domain查询用户设置
     * 
     * @param userId
     * @param domain
     * @return
     */
    UserSetting query(Long userId, String domain);

}
