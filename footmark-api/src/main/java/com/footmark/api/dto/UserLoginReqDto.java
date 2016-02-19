package com.footmark.api.dto;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 用户登录信息
 * @author carlos.chu
 * @version 1.0 2015年11月13日 下午3:14:09
 */
public class UserLoginReqDto extends RequestDto {

    private static final long serialVersionUID = 7901536986790840859L;

    @Validatable(description = "电话号", isMobileNo = true)
    private String telNum;
    @Validatable
    private String password;

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
