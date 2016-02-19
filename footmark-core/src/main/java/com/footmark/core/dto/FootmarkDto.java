package com.footmark.core.dto;

import java.util.Date;

/**
 * @Description: 用户脚印数据值
 * @author carlos.chu
 * @date 2015年9月10日
 */
public class FootmarkDto {

    private long id;

    private String content;

    private Long userId;

    private Long coordinateId;

    private Long effectiveTime;

    protected Date createTime;

    private String longitude;

    private String latitude;

    private double height;

    private String location;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getCoordinateId() {
        return coordinateId;
    }

    public void setCoordinateId(Long coordinateId) {
        this.coordinateId = coordinateId;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\",\"content\":\"" + content + "\",\"userId\":\"" + userId
                + "\",\"coordinateId\":\"" + coordinateId + "\",\"effectiveTime\":\"" + effectiveTime
                + "\",\"createTime\":\"" + createTime + "\",\"longitude\":\"" + longitude + "\",\"latitude\":\""
                + latitude + "\",\"height\":\"" + height + "\",\"location\":\"" + location + "\"} ";
    }

}
