package com.footmark.core.entity;

import com.footmark.common.entity.IDEntity;
import com.footmark.core.enums.GenderEnum;
import com.footmark.core.enums.UserLevelEnum;
import com.footmark.core.enums.UserStatusEnum;

/**
 * @Description: 用户信息
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class UserInfo extends IDEntity {

    private static final long serialVersionUID = 1324366261866824435L;

    private String accountNo;

    private String name;

    private GenderEnum gender;

    private String telNum;

    private String email;

    private String password;

    private String avatar;

    private UserLevelEnum level;

    private UserStatusEnum status;

    public UserInfo() {
        init(this);
        setStatus(UserStatusEnum.NORMAL);
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum == null ? null : telNum.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public UserLevelEnum getLevel() {
        return level;
    }

    public void setLevel(UserLevelEnum level) {
        this.level = level;
    }

    public UserStatusEnum getStatus() {
        return status;
    }

    public void setStatus(UserStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{\"accountNo\":\"" + accountNo + "\",\"name\":\"" + name + "\",\"gender\":\"" + gender
                + "\",\"telNum\":\"" + telNum + "\",\"email\":\"" + email + "\",\"password\":\"" + password
                + "\",\"avatar\":\"" + avatar + "\",\"level\":\"" + level + "\",\"id\":\"" + id
                + "\",\"createTime\":\"" + createTime + "\",\"status\":\"" + status + "\"} ";
    }

}