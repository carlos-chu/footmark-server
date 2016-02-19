package com.footmark.api.dto;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 私信请求
 * @author carlos.chu
 * @version 1.0 2016年1月11日 下午4:42:01
 */
public class LetterReqDto extends RequestDto {

    private static final long serialVersionUID = 2676550687620754731L;

    @Validatable(description = "发送者", isNumber = true)
    private Long fromUid;

    @Validatable(description = "接受者", isNumber = true)
    private Long toUid;

    @Validatable(description = "父id", isNumber = true, nullable = true)
    private Long parentId;

    @Validatable(description = "内容")
    private String content;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "{\"fromUid\":\"" + fromUid + "\",\"toUid\":\"" + toUid + "\",\"parentId\":\"" + parentId
                + "\",\"content\":\"" + content + "\",\"toString\":\"" + super.toString() + "\"}";
    }

}
