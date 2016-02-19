package com.footmark.core.entity;

import com.footmark.common.entity.IDEntity;
import com.footmark.core.enums.AcceptTypeEnum;
import com.footmark.core.enums.AcceptedFootmarkStatusEnum;
import com.footmark.core.enums.ReadStatusEnum;

/**
 * @Description: 用户接受的脚印
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class AcceptedFootmark extends IDEntity {
    /**
     * 
     */
    private static final long serialVersionUID = 7584606579348050439L;

    private Long userId;

    private Long footmarkId;

    private AcceptTypeEnum acceptType;

    private ReadStatusEnum readStatus;

    private AcceptedFootmarkStatusEnum status;

    public AcceptedFootmark() {
        init(this);
        setStatus(AcceptedFootmarkStatusEnum.NORMAL);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFootmarkId() {
        return footmarkId;
    }

    public void setFootmarkId(Long footmarkId) {
        this.footmarkId = footmarkId;
    }

    public AcceptTypeEnum getAcceptType() {
        return acceptType;
    }

    public void setAcceptType(AcceptTypeEnum acceptType) {
        this.acceptType = acceptType;
    }

    public ReadStatusEnum getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(ReadStatusEnum readStatus) {
        this.readStatus = readStatus;
    }

    public AcceptedFootmarkStatusEnum getStatus() {
        return status;
    }

    public void setStatus(AcceptedFootmarkStatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{\"userId\":\"" + userId + "\",\"footmarkId\":\"" + footmarkId + "\",\"acceptType\":\"" + acceptType
                + "\",\"readStatus\":\"" + readStatus + "\",\"status\":\"" + status + "\",\"id\":\"" + id
                + "\",\"createTime\":\"" + createTime + "\"} ";
    }

}