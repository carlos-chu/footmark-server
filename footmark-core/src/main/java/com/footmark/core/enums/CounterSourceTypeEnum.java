package com.footmark.core.enums;

/**
 * @Description: 计数服务源枚举
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum CounterSourceTypeEnum {

    FOOTMARK(1, "脚印"), COMMENT(2, "评论"), HOMEPAGE(3, "用户主页");

    private Integer code;
    private String desc;

    private CounterSourceTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CounterSourceTypeEnum toEnum(Integer code) {
        for (CounterSourceTypeEnum category : CounterSourceTypeEnum.values()) {
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
