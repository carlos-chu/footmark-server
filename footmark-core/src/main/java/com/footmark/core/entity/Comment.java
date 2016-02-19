package com.footmark.core.entity;

import com.footmark.common.entity.Entity;
import com.footmark.core.enums.CommentSourceTypeEnum;

/**
 * @Description: 脚印的评论
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class Comment extends Entity {

    private static final long serialVersionUID = -3108209375177097970L;

    private Long userId;

    private Long sourceId;

    private CommentSourceTypeEnum sourceType;

    private String content;

    private Long parentId;

    private Long targetUserId;

    public Comment() {
        init(this);
    }

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

    public CommentSourceTypeEnum getSourceType() {
        return sourceType;
    }

    public void setSourceType(CommentSourceTypeEnum sourceType) {
        this.sourceType = sourceType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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
                + targetUserId + "\",\"status\":\"" + status + "\",\"id\":\"" + id + "\",\"createTime\":\""
                + createTime + "\",\"updateTime\":\"" + updateTime + "\"}";
    }

}