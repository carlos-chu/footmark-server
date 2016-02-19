package com.footmark.core.enums;

/**
 * @Description: 好友关系类型
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum RelationTypeEnum {

    ACTIVE(1, "主动发起加好友"), 
    PASSIVE(2, "被请求加好友"), 
    ACTIVE_BLACKLIST(3, "主动设置黑名单"),
    PASSIVE_BLACKLIST(4, "被设置为黑名单"),
    ;

    private Integer code;
    private String desc;

    private RelationTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static RelationTypeEnum toEnum(Integer code) {
        for (RelationTypeEnum category : RelationTypeEnum.values()) {
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
