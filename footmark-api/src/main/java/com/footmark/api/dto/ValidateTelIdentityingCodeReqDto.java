package com.footmark.api.dto;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 校验用户验证码Dto
 * @author carlos.chu
 * @date 2015年8月19日
 */
public class ValidateTelIdentityingCodeReqDto extends RequestDto {

    private static final long serialVersionUID = -7529350333519233556L;

    @Validatable(description = "手机号", isMobileNo = true)
    private String mobileNo;
    @Validatable(description = "验证码", isNumber = true)
    private String identityingCode;

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getIdentityingCode() {
        return identityingCode;
    }

    public void setIdentityingCode(String identityingCode) {
        this.identityingCode = identityingCode;
    }

}
