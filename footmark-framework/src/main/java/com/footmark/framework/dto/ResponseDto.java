package com.footmark.framework.dto;

import java.io.Serializable;

import com.footmark.common.enums.ExceptionEnum;
import com.footmark.common.exception.BusinessException;

/**
 * @Description: 平台响应DTO
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class ResponseDto<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8929398155535658080L;

    private boolean ret;
    private T data;
    private String errCode;
    private String errMsg;

    public ResponseDto<T> err(ExceptionEnum exceptionEnum) {
        return err(exceptionEnum.getCode(), exceptionEnum.getMessage());
    }

    public ResponseDto<T> err(BusinessException exception) {
        return err(exception.getErrCode(), exception.getErrChineseMsg());
    }

    public ResponseDto<T> err(Throwable exception) {
        return err(ExceptionEnum.SYS_ERR);
    }

    private ResponseDto<T> err(String errCode, String errMsg) {
        setRet(false);
        setErrCode(errCode);
        setErrMsg(errMsg);
        return this;
    }

    public ResponseDto<T> succ(T data) {
        setRet(true);
        setData(data);
        return this;
    }

    public boolean isRet() {
        return ret;
    }

    public void setRet(boolean ret) {
        this.ret = ret;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "{\"ret\":\"" + ret + "\",\"data\":\"" + data + "\",\"errCode\":\"" + errCode + "\",\"errMsg\":\""
                + errMsg + "\"}";
    }

}
