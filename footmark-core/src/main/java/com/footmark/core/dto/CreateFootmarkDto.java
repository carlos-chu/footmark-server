package com.footmark.core.dto;

import com.footmark.core.entity.Footmark;

/**
 * @Description: 创建脚印的Dto
 * @author carlos.chu
 * @date 2015年8月21日
 */
public class CreateFootmarkDto extends Footmark {

    private static final long serialVersionUID = 4857953409623586957L;
    // 坐标-经度
    private String longitude;
    // 坐标-纬度
    private String latitude;
    // 坐标-位置信息
    private String location;
    // 坐标-高度
    private double height;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "{\"longitude\":\"" + longitude + "\",\"latitude\":\"" + latitude + "\",\"height\":\"" + height
                + "\",\"id\":\"" + id + "\",\"createTime\":\"" + createTime + "\",\"toString()\":\"" + super.toString()
                + "\"} ";
    }

}
