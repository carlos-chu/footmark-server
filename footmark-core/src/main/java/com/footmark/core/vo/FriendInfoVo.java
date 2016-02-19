package com.footmark.core.vo;

import com.footmark.framework.vo.EntityVo;

/**
 * @Description: 用户信息展现类
 * @author carlos.chu
 * @date 2015年9月11日
 */
public class FriendInfoVo implements EntityVo {

    private static final long serialVersionUID = -7266399676659210404L;

    private Long id;

    private String name;

    private String avatar;

    private int gender;

    private String telNum;

    private String accountNo;
    /**
     * 和客户端登录用户的关系,0：未发送，1：未接受，2：已接受，3：拒绝
     */
    private int relationStatus;
    /**
     * 关系类型,1:主动发起加好友, 2:被请求加好友, 3:主动设置黑名单, 4:被设置为黑名单
     */
    private int relationType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getTelNum() {
        return telNum;
    }

    public void setTelNum(String telNum) {
        this.telNum = telNum;
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public int getRelationStatus() {
        return relationStatus;
    }

    public void setRelationStatus(int relationStatus) {
        this.relationStatus = relationStatus;
    }

    public int getRelationType() {
        return relationType;
    }

    public void setRelationType(int relationType) {
        this.relationType = relationType;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\",\"name\":\"" + name + "\",\"avatar\":\"" + avatar + "\",\"gender\":\"" + gender
                + "\",\"telNum\":\"" + telNum + "\",\"accountNo\":\"" + accountNo + "\",\"relationStatus\":\""
                + relationStatus + "\",\"relationType\":\"" + relationType + "\",\"toString\":\"" + super.toString()
                + "\"}";
    }

}
