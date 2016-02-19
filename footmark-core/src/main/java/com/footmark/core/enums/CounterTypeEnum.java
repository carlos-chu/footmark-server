package com.footmark.core.enums;

/**
 * @Description: 计数类型枚举
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum CounterTypeEnum {

    LOVE(1, "点赞"), COMMENT(2, "评论");

    private Integer code;
    private String desc;

    private CounterTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CounterTypeEnum toEnum(Integer code) {
        for (CounterTypeEnum category : CounterTypeEnum.values()) {
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
