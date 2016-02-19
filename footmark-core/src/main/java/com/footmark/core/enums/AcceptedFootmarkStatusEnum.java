package com.footmark.core.enums;

/**
 * @Description: 接受脚印的状态
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum AcceptedFootmarkStatusEnum {

    NORMAL(0, "正常"), ACCEPTER_DEL(1, "接受用户删除"), PROMULGATOR_DEL(2, "发布者删除"), EXPIRE(3, "实效到期");

    private Integer code;
    private String desc;

    private AcceptedFootmarkStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AcceptedFootmarkStatusEnum toEnum(Integer code) {
        for (AcceptedFootmarkStatusEnum category : AcceptedFootmarkStatusEnum.values()) {
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
