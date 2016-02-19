package com.footmark.core.entity;

import com.footmark.common.entity.Entity;
import com.footmark.core.enums.UserSettingEnum;

/**
 * @Description: 用户设置
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class UserSetting extends Entity {

    private static final long serialVersionUID = -7430259413973105526L;

    /**
     * 用户设置的接受范围
     */
    public static final String SCOPE = "SCOPE";
    /**
     * 用户接受的性别
     */
    public static final String GENDER_CATEGORY = "GENDER_CATEGORY";

    private Long userId;

    private String domain;

    private UserSettingEnum key;

    private Integer value;

    public UserSetting() {
        init(this);
    }

    public UserSetting(Long uid, UserSettingEnum key) {
        init(this);
        setKey(key);
        setUserId(uid);
        setDomain(key.getDomain());
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public UserSettingEnum getKey() {
        return key;
    }

    public void setKey(UserSettingEnum key) {
        this.key = key;
        this.value = getValue();
    }

    public Integer getValue() {
        return key.getValue();
    }

    @Override
    public String toString() {
        return "{\"userId\":\"" + userId + "\",\"key\":\"" + key + "\",\"value\":\"" + value + "\",\"status\":\""
                + status + "\",\"id\":\"" + id + "\"} ";
    }

}