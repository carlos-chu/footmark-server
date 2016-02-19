package com.footmark.framework.dto;

import java.io.Serializable;

import com.footmark.framework.annotation.Validatable;

/**
 * @Description: 数据进入系统的公共Dto
 * @author carlos.chu
 * @date 2015年8月18日
 */
public class RequestDto implements Serializable {

    private static final long serialVersionUID = 8630658396645418733L;

    @Validatable(description = "请求号,可为空", nullable = true)
    private String reqNo;

    public String getReqNo() {
        return reqNo;
    }

    public void setReqNo(String reqNo) {
        this.reqNo = reqNo;
    }

    @Override
    public String toString() {
        return "{\"reqNo\":\"" + reqNo + "\"} ";
    }

}
