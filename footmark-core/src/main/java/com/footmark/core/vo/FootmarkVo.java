package com.footmark.core.vo;

import java.util.List;

import com.footmark.framework.vo.EntityVo;

/**
 * @Description: 脚印展现Dto
 * @author carlos.chu
 * @date 2015年8月31日
 */
public class FootmarkVo implements EntityVo, Comparable<FootmarkVo> {

    private static final long serialVersionUID = 1225712666578928272L;

    // 用户相关
    private Long userId;

    private String userName;

    private String avatar;

    private Integer gender;

    // 脚印相关
    private Long footmarkId;

    private String content;

    private String createTime;

    private List<MultiMediaVo> multiMedias;

    // 社交相关
    private String distance; // 距离

    private Long likeId; // 点赞id

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public Long getFootmarkId() {
        return footmarkId;
    }

    public void setFootmarkId(Long footmarkId) {
        this.footmarkId = footmarkId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<MultiMediaVo> getMultiMedias() {
        return multiMedias;
    }

    public void setMultiMedias(List<MultiMediaVo> multiMedias) {
        this.multiMedias = multiMedias;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public Long getLikeId() {
        return likeId;
    }

    public void setLikeId(Long likeId) {
        this.likeId = likeId;
    }

    @Override
    public String toString() {
        return "{\"userId\":\"" + userId + "\",\"userName\":\"" + userName + "\",\"avatar\":\"" + avatar
                + "\",\"footmarkId\":\"" + footmarkId + "\",\"content\":\"" + content + "\",\"createTime\":\""
                + createTime + "\",\"multiMediaVos\":\"" + multiMedias + "\",\"distance\":\"" + distance
                + "\",\"likeId\":\"" + likeId + "\"} ";
    }

    @Override
    public int compareTo(FootmarkVo o) {
        return getDistance().compareTo(o.getDistance());
    }

}
