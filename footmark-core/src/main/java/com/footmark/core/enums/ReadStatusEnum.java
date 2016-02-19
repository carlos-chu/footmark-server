package com.footmark.core.enums;

/**
 * @Description: 脚印的阅读状态
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum ReadStatusEnum {

    UNREAD(0, "未读"), READED(1, "已读"), IGNORE(2, "忽略");

    private Integer code;
    private String desc;

    private ReadStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ReadStatusEnum toEnum(Integer code) {
        for (ReadStatusEnum category : ReadStatusEnum.values()) {
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
