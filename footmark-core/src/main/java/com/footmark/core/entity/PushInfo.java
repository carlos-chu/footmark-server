package com.footmark.core.entity;

import com.footmark.common.entity.IDEntity;

/**
 * @Description: 推送设备信息
 * @author carlos.chu
 * @version 1.0 2015年12月30日 下午6:39:13
 */
public class PushInfo extends IDEntity {

    private static final long serialVersionUID = 1460597676081486687L;

    private Long userId;

    private String bdUserId;

    private String bdChannelId;

    public PushInfo() {
        super.init(this);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getBdUserId() {
        return bdUserId;
    }

    public void setBdUserId(String bdUserId) {
        this.bdUserId = bdUserId;
    }

    public String getBdChannelId() {
        return bdChannelId;
    }

    public void setBdChannelId(String bdChannelId) {
        this.bdChannelId = bdChannelId;
    }

    @Override
    public String toString() {
        return "{\"userId\":\"" + userId + "\",\"bdUserId\":\"" + bdUserId + "\",\"bdChannelId\":\"" + bdChannelId
                + "\",\"toString\":\"" + super.toString() + "\"}";
    }

}
