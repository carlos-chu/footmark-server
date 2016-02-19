package com.footmark.core.enums;

/**
 * @Description: 用户评论源类型枚举
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum CommentSourceTypeEnum {

    FOOTMARK(1, "脚印"), COMMENT(2, "评论");

    private Integer code;
    private String desc;

    private CommentSourceTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static CommentSourceTypeEnum toEnum(Integer code) {
        for (CommentSourceTypeEnum category : CommentSourceTypeEnum.values()) {
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
