package com.footmark.core.dto;

import java.io.Serializable;

/**
 * @Description: 推送脚印requestDto
 * @author carlos.chu
 * @date 2015年8月31日
 */
public class FootmarkPullDto implements Serializable {

    private static final long serialVersionUID = 1225712666578928272L;

    private long userId;

    private String longitude;

    private String latitude;

    private int page;

    private int size;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

}
