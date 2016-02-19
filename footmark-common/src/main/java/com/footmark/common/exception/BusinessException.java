package com.footmark.common.exception;

import com.footmark.common.enums.ExceptionEnum;

/**
 * @Description: 服务端业务异常总定义
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = 8934748636330408223L;
    /** a defined error code */
    private String errCode;
    /** en error message */
    private String message;
    /** human-readable error message */
    private String errChineseMsg;

    public BusinessException(ExceptionEnum errEnum) {
        this(errEnum.getCode(), errEnum.getMessage(), errEnum.getChineseMessage());
    }

    public BusinessException(ExceptionEnum errEnum, String message) {
        this(errEnum.getCode(), message, message);
    }

    private BusinessException(String errCode, String message, String chineseMsg) {
        super(message);
        this.errCode = errCode;
        this.message = message;
        this.errChineseMsg = chineseMsg;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getErrChineseMsg() {
        return errChineseMsg;
    }

    public String getErrMsg() {
        return message;
    }
}
