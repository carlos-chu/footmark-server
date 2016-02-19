package com.footmark.core.enums;

import com.footmark.core.entity.UserSetting;

/**
 * @Description: 用户设置枚举
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum UserSettingEnum {
    
    ACCEPT_SCOPE_1000(1, 1000, UserSetting.SCOPE, "用户接受脚印范围1km"),
    ACCEPT_SCOPE_500(2, 500, UserSetting.SCOPE, "用户接受范围500m"),
    ACCEPT_SCOPE_100(3, 100, UserSetting.SCOPE, "用户接受范围100m"),
    ACCEPT_CATEGORY_ALL(4, AccepterCategoryEnum.ALL.getCode(), UserSetting.GENDER_CATEGORY, "接受所有用户的脚印"),
    ACCEPT_CATEGORY_MALE(5, AccepterCategoryEnum.MALE.getCode(), UserSetting.GENDER_CATEGORY, "只接受男生发表的脚印"),
    ACCEPT_CATEGORY_FAMALE(6, AccepterCategoryEnum.FAMALE.getCode(), UserSetting.GENDER_CATEGORY, "只接受女生发表的脚印"),
    
    ;

    private Integer key;
    private Integer value;
    private String domain;
    private String desc;

    /**
     * @param key
     * @param value
     * @param domain 所属域
     * @param desc
     */
    private UserSettingEnum(Integer key, Integer value, String domain, String desc) {
        this.key = key;
        this.value = value;
        this.domain = domain;
        this.desc = desc;
    }

    public static UserSettingEnum toEnum(Integer key) {
        for (UserSettingEnum category : UserSettingEnum.values()) {
            if (category.getKey().equals(key)) {
                return category;
            }
        }
        return null;
    }

    public Integer getKey() {
        return key;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
