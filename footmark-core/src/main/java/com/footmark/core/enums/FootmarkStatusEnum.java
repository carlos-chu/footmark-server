package com.footmark.core.enums;

/**
 * @Description: 脚印状态类型
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum FootmarkStatusEnum {

    NORMAL(0, "正常"), 
    USER_DEL(1, "用户删除"),
    EXPIRE(2, "实效到期"),
    DEL(3, "强制删除"),
    REPORT_PENDING_AUDIT(4, "举报待审核"),
    REPORTED(5, "举报通过"),
    ;

    private Integer code;
    private String desc;

    private FootmarkStatusEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static FootmarkStatusEnum toEnum(Integer code) {
        for (FootmarkStatusEnum category : FootmarkStatusEnum.values()) {
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
