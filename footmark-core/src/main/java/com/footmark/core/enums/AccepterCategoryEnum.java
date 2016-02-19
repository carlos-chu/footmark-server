package com.footmark.core.enums;

/**
 * @Description: 接受者的类别
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum AccepterCategoryEnum {

    ALL(1, "全部"), FAMALE(2, "女生"), MALE(3, "男生");

    private Integer code;
    private String desc;

    private AccepterCategoryEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AccepterCategoryEnum toEnum(Integer code) {
        for (AccepterCategoryEnum category : AccepterCategoryEnum.values()) {
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
