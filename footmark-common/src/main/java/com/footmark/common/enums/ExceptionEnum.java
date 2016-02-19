package com.footmark.common.enums;

/**
 * @Description: 异常枚举
 * @author carlos.chu
 * @date 2015年8月17日
 */
public enum ExceptionEnum {
    //系统级的异常，01开头
    SUCESS("00000", "SUCCESS", "成功"),
    SYS_ERR("01001", "SYS_ERR", "系统异常"),
    PARAM_ERR("01002", "PARAM_ERR", "参数校验异常"),
    ENUM_TRANSFER_ERR("01003", "ENUM_TRANSFER_ERR", "枚举值转换异常"),
    DATA_ISEMPTY_ERR("01004", "DATA_ISEMPTY_ERR", "源数据为空异常"),
    
    //业务异常，02开头
    MOBILENO_EXITED_ERR("02001", "MOBILENO_EXITED_ERR", "手机号存在"),
    LOVE_REPEAT_ERR("02002", "LOVE_REPEAT_ERR", "重复点赞异常"),
    USER_NOT_FOUND_ERR("02003", "USER_NOT_FOUND_ERR", "用户不存在"),
    UPDATE_DATA_ERR("02004", "UPDATE_DATA_ERR", "更新数据异常"),
    USER_LOGIN_ERR("02005", "USER_LOGIN_ERR", "账号或密码错误"),
    USER_FRIEND_NOT_FOUND_ERR("02006", "USER_FRIEND_NOT_FOUND_ERR", "朋友关系不存在"),
    //网关异常，03开头
    
    ;
    
    private final String code;
    private final String message;
    private final String chineseMessage;

    private ExceptionEnum(String code, String desc, String chineseMessage) {
        this.code = code;
        this.message = desc;
        this.chineseMessage = chineseMessage;
    }

    /**
     * Get the error code.
     * @return error code
     */
    public String getCode() {
        return code;
    }

    /**
     * Description of the error.
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Chinese description.
     * @return the chineseMessage
     */
    public String getChineseMessage() {
        return chineseMessage;
    }
}
