package com.footmark.core.enums;

/**
 * @Description: 多媒体类型
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum MultiMediaTypeEnum {

    VOICE(1, "语音"), PICTURE(2, "图片"), VIDEO(3, "视频");

    private Integer code;
    private String desc;

    private MultiMediaTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static MultiMediaTypeEnum toEnum(Integer code) {
        for (MultiMediaTypeEnum category : MultiMediaTypeEnum.values()) {
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
