package com.footmark.api.dto;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 推送信息DTO
 * @author carlos.chu
 * @version 1.0 2015年12月25日 下午2:10:46
 */
public class PushInfoReqDto extends RequestDto {

    private static final long serialVersionUID = 7138494895833072793L;

    @Validatable(description = "用户ID", isNumber = true)
    private Long userId;

    @Validatable(description = "百度userID")
    private String bdUserId;

    @Validatable(description = "百度channelID")
    private String bdChannelId;

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
