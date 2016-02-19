package com.footmark.api.dto;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 推送脚印requestDto
 * @author carlos.chu
 * @date 2015年8月31日
 */
public class FootmarkReqDto extends RequestDto {

    private static final long serialVersionUID = 1225712666578928272L;

    @Validatable(description = "用户ID")
    private Long userId;

    @Validatable(description = "经度")
    private String longitude;

    @Validatable(description = "纬度")
    private String latitude;

    @Validatable(description = "页数", isNumber = true)
    private Integer page;

    @Validatable(description = "每页行数", isNumber = true)
    private Integer size;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

}
