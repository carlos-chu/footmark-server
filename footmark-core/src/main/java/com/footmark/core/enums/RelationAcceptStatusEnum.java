package com.footmark.core.enums;

/**
 * @Description: 加好友接受状态
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum RelationAcceptStatusEnum {

    UNDO(0, "初始化"), UNACCEPT(1, "未接受"), ACCEPTED(2, "已接受"), REFUSE(3, "拒绝");

    private Integer code;
    private String desc;

    private RelationAcceptStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RelationAcceptStatusEnum toEnum(Integer code) {
        for (RelationAcceptStatusEnum category : RelationAcceptStatusEnum.values()) {
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
