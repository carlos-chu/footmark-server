package com.footmark.core.enums;

/**
 * @Description: 接受类型
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum AcceptTypeEnum {

    COMMON(0, "普通接受"), ROAM(1, "漫游接受"), HISTORY_PLACE_PUSH(2, "历史地点推送"), DIRECTIONAL(3, "发布者定向推送");

    private Integer code;
    private String desc;

    private AcceptTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AcceptTypeEnum toEnum(Integer code) {
        for (AcceptTypeEnum category : AcceptTypeEnum.values()) {
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
