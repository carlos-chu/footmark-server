package com.footmark.api.dto;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 推送信息DTO
 * @author carlos.chu
 * @version 1.0 2015年12月25日 下午2:10:46
 */
public class PushMsgReqDto extends RequestDto {

    private static final long serialVersionUID = 7138494895833072793L;

    @Validatable(description = "来源用户ID", isNumber = true)
    private Long fromUid;

    @Validatable(description = "目标用户ID", isNumber = true)
    private Long toUid;

    @Validatable(description = "推送消息描述类型", isNumber = true)
    private int pushType;

    public Long getFromUid() {
        return fromUid;
    }

    public void setFromUid(Long fromUid) {
        this.fromUid = fromUid;
    }

    public Long getToUid() {
        return toUid;
    }

    public void setToUid(Long toUid) {
        this.toUid = toUid;
    }

    public int getPushType() {
        return pushType;
    }

    public void setPushType(int pushType) {
        this.pushType = pushType;
    }

    @Override
    public String toString() {
        return "{\"fromUid\":\"" + fromUid + "\",\"toUid\":\"" + toUid + "\",\"pushType\":\"" + pushType
                + "\",\"toString\":\"" + super.toString() + "\"}";
    }

}
