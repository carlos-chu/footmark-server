package com.footmark.core.enums;

/**
 * @Description: 提示类型枚举
 * @author carlos.chu
 * @version 1.0 2015年12月31日 下午3:19:43
 */
public enum PromptTypeEnum {

    APPLY_FRIEND(1, "申请添加好友"), AGTEE_FRIEND(2, "同意添加好友"), REFUSE_FRIEND(3, "拒绝添加好友"), PRIVATE_LETTER(4, "私信"), COMMENT(
            5, "评论");

    private Integer code;
    private String message;

    PromptTypeEnum(Integer code, String messge) {
        this.code = code;
        this.message = messge;
    }

    public static PromptTypeEnum toEnum(Integer code) {
        for (PromptTypeEnum pushType : PromptTypeEnum.values()) {
            if (pushType.getCode().equals(code)) {
                return pushType;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
