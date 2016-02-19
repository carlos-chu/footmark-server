package com.footmark.core.enums;

/**
 * @Description: 用户级别枚举
 * @author carlos.chu
 * @date 2015年8月20日
 */
public enum UserLevelEnum {

    VALIDDATED_MOBILENO(0, "验证过手机"), 
    IMPROVE_INFO(1, "完善基本信息"),
    ;

    private Integer code;
    private String desc;

    private UserLevelEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UserLevelEnum toEnum(Integer code) {
        for (UserLevelEnum category : UserLevelEnum.values()) {
            if (category.getCode().equals(code)) {
                return category;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
