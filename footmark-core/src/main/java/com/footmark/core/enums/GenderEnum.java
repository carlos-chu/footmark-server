package com.footmark.core.enums;

/**
 * @Description: 性别枚举
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum GenderEnum {

    FAMALE(1, "女"), MALE(2, "男");

    private Integer code;
    private String desc;

    private GenderEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static GenderEnum toEnum(Integer code) {
        for (GenderEnum category : GenderEnum.values()) {
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
