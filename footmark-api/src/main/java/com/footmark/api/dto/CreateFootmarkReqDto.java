package com.footmark.api.dto;

import com.footmark.core.enums.AccepterCategoryEnum;
import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 创建脚印的requestDto
 * @author carlos.chu
 * @date 2015年8月21日
 */
public class CreateFootmarkReqDto extends RequestDto {

    private static final long serialVersionUID = -634851331134854391L;

    @Validatable(description = "标题", nullable = true)
    private String title;

    @Validatable(description = "内容")
    private String content;

    @Validatable(isNumber = true)
    private Long userId;

    @Validatable(description = "指定接受者种类", isNumber = true, enumScope = AccepterCategoryEnum.class)
    private Integer accepterCategory;

    @Validatable(description = "有效时间", nullable = true)
    private Long effectiveTime;

    @Validatable(description = "经度")
    private String longitude;

    @Validatable(description = "纬度")
    private String latitude;

    @Validatable(description = "位置信息", nullable = true)
    private String location;

    @Validatable(description = "高度", nullable = true)
    private String height;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Integer getAccepterCategory() {
        return accepterCategory;
    }

    public void setAccepterCategory(Integer accepterCategory) {
        this.accepterCategory = accepterCategory;
    }

    public Long getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(Long effectiveTime) {
        this.effectiveTime = effectiveTime;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

}
