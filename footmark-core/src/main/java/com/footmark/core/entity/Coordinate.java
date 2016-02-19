package com.footmark.core.entity;

import com.footmark.common.entity.Entity;

/**
 * @Description: 坐标
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class Coordinate extends Entity{

    private static final long serialVersionUID = 207470136931111228L;

    private String longitude;

    private String latitude;

    private double height;

    private String location;

    public Coordinate() {
        init(this);
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
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
        this.location = location == null ? null : location.trim();
    }

}