package com.footmark.core.enums;

/**
 * @Description: 用户状态枚举
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum UserStatusEnum {

    NORMAL(0, "正常"), FROZEN(1, "冻结"), CANCEL(2, "注销");

    private Integer code;
    private String desc;

    private UserStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static UserStatusEnum toEnum(Integer code) {
        for (UserStatusEnum category : UserStatusEnum.values()) {
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
