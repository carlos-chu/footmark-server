package com.footmark.core.enums;

/**
 * @Description: 用户脚印类型枚举
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum FootmarkTypeEnum {

    USER(0, "用户发布"), SYSTEM(1, "系统发布");

    private Integer code;
    private String desc;

    private FootmarkTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static FootmarkTypeEnum toEnum(Integer code) {
        for (FootmarkTypeEnum category : FootmarkTypeEnum.values()) {
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
