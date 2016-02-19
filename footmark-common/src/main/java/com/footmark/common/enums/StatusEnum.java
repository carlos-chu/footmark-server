package com.footmark.common.enums;

/**
 * @Description: 状态类型
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum StatusEnum {

    NORMAL(0, "正常"), DEL(1, "删除/失效/解除"),  REPORT(2, "举报");

    private Integer code;
    private String desc;

    private StatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static StatusEnum toEnum(Integer code) {
        for (StatusEnum category : StatusEnum.values()) {
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
