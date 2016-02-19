package com.footmark.api.dto;

import com.footmark.core.enums.CommentSourceTypeEnum;
import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 评论请求
 * @author carlos.chu
 * @version 1.0 2016年1月11日 下午4:42:01
 */
public class CommentReqDto extends RequestDto {

    private static final long serialVersionUID = 2676550687620754731L;

    @Validatable(description = "评论用户id")
    private Long userId;

    @Validatable(description = "评论源")
    private Long sourceId;

    @Validatable(description = "源类型", isNumber = true, enumScope = CommentSourceTypeEnum.class)
    private int sourceType;

    @Validatable
    private String content;

    @Validatable(description = "父评论ID", nullable = true)
    private Long parentId;

    @Validatable(description = "回复的目标用户ID", nullable = true)
    private Long targetUserId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
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

    public Long getTargetUserId() {
        return targetUserId;
    }

    public void setTargetUserId(Long targetUserId) {
        this.targetUserId = targetUserId;
    }

    @Override
    public String toString() {
        return "{\"userId\":\"" + userId + "\",\"sourceId\":\"" + sourceId + "\",\"sourceType\":\"" + sourceType
                + "\",\"content\":\"" + content + "\",\"parentId\":\"" + parentId + "\",\"targetUserId\":\""
                + targetUserId + "\",\"toString\":\"" + super.toString() + "\"}";
    }

}
