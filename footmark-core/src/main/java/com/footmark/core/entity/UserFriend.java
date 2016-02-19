package com.footmark.core.entity;

import com.footmark.common.entity.Entity;
import com.footmark.core.enums.RelationAcceptStatusEnum;
import com.footmark.core.enums.RelationTypeEnum;

/**
 * @Description: 好友
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class UserFriend extends Entity {

    private static final long serialVersionUID = 8970479046334764510L;

    private Long userId;

    private Long friendId;

    private RelationAcceptStatusEnum acceptStatus;

    private RelationTypeEnum relationType;

    public UserFriend() {
        init(this);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public RelationTypeEnum getRelationType() {
        return relationType;
    }

    public void setRelationType(RelationTypeEnum relationType) {
        this.relationType = relationType;
    }

    public RelationAcceptStatusEnum getAcceptStatus() {
        return acceptStatus;
    }

    public void setAcceptStatus(RelationAcceptStatusEnum acceptStatus) {
        this.acceptStatus = acceptStatus;
    }

    @Override
    public String toString() {
        return "UserFriend [userId=" + userId + ", friendId=" + friendId + ", acceptStatus=" + acceptStatus
                + ", relationType=" + relationType + ", id=" + id + ", createTime=" + createTime + ", updateTime="
                + updateTime + ", status=" + status + "]";
    }

}