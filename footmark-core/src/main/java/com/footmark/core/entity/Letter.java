package com.footmark.core.entity;

import com.footmark.common.entity.Entity;
import com.footmark.core.enums.ReadStatusEnum;

/**
 * @Description: 私信
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class Letter extends Entity {

    private static final long serialVersionUID = -5191379951621623586L;

    private Long parentId;

    private Long fromUid;

    private Long toUid;

    private String content;

    private ReadStatusEnum readStatus;

    public Letter() {
        init(this);
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

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
        this.content = content == null ? null : content.trim();
    }

    public ReadStatusEnum getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(ReadStatusEnum readStatus) {
        this.readStatus = readStatus;
    }

}