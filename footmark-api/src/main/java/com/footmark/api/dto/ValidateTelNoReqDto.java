package com.footmark.api.dto;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 校验用户手机号DTO
 * @author carlos.chu
 * @date 2015年8月19日
 */
public class ValidateTelNoReqDto extends RequestDto {

    private static final long serialVersionUID = -7529350333519233556L;

    @Validatable(isMobileNo = true)
    private String mobileNo;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

}
