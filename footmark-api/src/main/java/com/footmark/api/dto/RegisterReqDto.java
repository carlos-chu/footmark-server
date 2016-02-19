package com.footmark.api.dto;

import com.footmark.core.enums.GenderEnum;
import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 注册的DTO
 * @author carlos.chu
 * @date 2015年8月20日
 */
public class RegisterReqDto extends RequestDto {

    private static final long serialVersionUID = 1356850212672297896L;

    @Validatable(description = "性别", nullable = true, enumScope = GenderEnum.class)
    private Integer gender;
    @Validatable(description = "姓名")
    private String name;
    @Validatable(description = "手机号", isMobileNo = true)
    private String telNum;
    @Validatable(description = "邮箱", nullable = true)
    private String email;
    @Validatable(description = "头像地址")
    private String headUrl;
    @Validatable(description = "密码")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadUrl() {
        return headUrl;
    }

    public void setHeadUrl(String headUrl) {
        this.headUrl = headUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "{\"gender\":\"" + gender + "\",\"name\":\"" + name + "\",\"telNum\":\"" + telNum + "\",\"email\":\""
                + email + "\",\"headUrl\":\"" + headUrl + "\",\"password\":\"" + password + "\"} ";
    }

}
