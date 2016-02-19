package com.footmark.core.enums;

/**
 * @Description: 源多媒体类型
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum SourceMultiTypeEnum {

    FOOTMARK(1, "足迹"), COMMENT(2, "评论");

    private Integer code;
    private String desc;

    private SourceMultiTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static SourceMultiTypeEnum toEnum(Integer code) {
        for (SourceMultiTypeEnum category : SourceMultiTypeEnum.values()) {
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
