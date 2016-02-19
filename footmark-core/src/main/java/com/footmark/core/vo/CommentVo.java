package com.footmark.core.vo;

import com.footmark.framework.vo.EntityVo;

/**
 * @Description: 评论VO
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class CommentVo implements EntityVo {

    private static final long serialVersionUID = -5191379951621623586L;

    private Long id;

    private String content;

    private String createTime;

    private Long userId;

    private String userName;

    private String userAvatar;

    private Long replyUserId;

    private String replyUserName;

    /**
     * 所属源，目前为sourceId+sourceType组成的字符串
     */
    private String uniqId;

    /**
     * 源数据的简述
     */
    private String source;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public Long getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Long replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getUniqId() {
        return uniqId;
    }

    public void setUniqId(String uniqId) {
        this.uniqId = uniqId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\",\"content\":\"" + content + "\",\"createTime\":\"" + createTime
                + "\",\"userId\":\"" + userId + "\",\"userName\":\"" + userName + "\",\"userAvatar\":\"" + userAvatar
                + "\",\"replyUserId\":\"" + replyUserId + "\",\"replyUserName\":\"" + replyUserName
                + "\",\"uniqId\":\"" + uniqId + "\",\"source\":\"" + source + "\"}";
    }

}