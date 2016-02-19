package com.footmark.core.vo;

import com.footmark.framework.vo.EntityVo;

/**
 * @Description: 私信VO
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class LetterVo implements EntityVo {

    private static final long serialVersionUID = -5191379951621623586L;

    private Long id;

    private Long fromUid;

    private String fromAvatar;

    private String fromName;

    private String content;

    private int readStatus;

    private String createTime;

    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFromUid() {
        return fromUid;
    }

    public void setFromUid(Long fromUid) {
        this.fromUid = fromUid;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(int readStatus) {
        this.readStatus = readStatus;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\",\"fromUid\":\"" + fromUid + "\",\"fromAvatar\":\"" + fromAvatar
                + "\",\"fromName\":\"" + fromName + "\",\"content\":\"" + content + "\",\"readStatus\":\"" + readStatus
                + "\",\"createTime\":\"" + createTime + "\",\"parentId\":\"" + parentId + "\"}";
    }

}