package com.footmark.core.entity;

import com.footmark.common.entity.IDEntity;
import com.footmark.core.enums.AccepterCategoryEnum;
import com.footmark.core.enums.FootmarkStatusEnum;
import com.footmark.core.enums.FootmarkTypeEnum;
import com.footmark.core.enums.GenderEnum;

/**
 * @Description: 脚印
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class Footmark extends IDEntity {

    private static final long serialVersionUID = 1255819218044877933L;

    private String title;

    private String content;

    private Long userId;

    private Long coordinateId;

    private AccepterCategoryEnum accepterCategory;

    private GenderEnum gender;

    private Long effectiveTime;

    private FootmarkTypeEnum type;

    private FootmarkStatusEnum status;

    public Footmark() {
        init(this);
        setStatus(FootmarkStatusEnum.NORMAL);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    public AccepterCategoryEnum getAccepterCategory() {
        return accepterCategory;
    }

    public void setAccepterCategory(AccepterCategoryEnum accepterCategory) {
        this.accepterCategory = accepterCategory;
    }

    public GenderEnum getGender() {
        return gender;
    }

    public void setGender(GenderEnum gender) {
        this.gender = gender;
    }

    public FootmarkTypeEnum getType() {
        return type;
    }

    public void setType(FootmarkTypeEnum type) {
        this.type = type;
    }

    public FootmarkStatusEnum getStatus() {
        return status;
    }

    public void setStatus(FootmarkStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{\"title\":\"" + title + "\",\"content\":\"" + content + "\",\"userId\":\"" + userId
                + "\",\"coordinateId\":\"" + coordinateId + "\",\"accepterCategory\":\"" + accepterCategory
                + "\",\"gender\":\"" + gender + "\",\"effectiveTime\":\"" + effectiveTime + "\",\"type\":\"" + type
                + "\",\"status\":\"" + status + "\",\"id\":\"" + id + "\",\"createTime\":\"" + createTime + "\"} ";
    }

}