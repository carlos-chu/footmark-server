package com.footmark.api.dto;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 好友申请DTO
 * @author carlos.chu
 * @version 1.0 2015年12月25日 下午2:10:46
 */
public class FriendReqDto extends RequestDto {

    private static final long serialVersionUID = 7138494895833072793L;

    @Validatable(description = "用户ID", isNumber = true)
    private Long userId;

    @Validatable(description = "好友ID", isNumber = true)
    private Long friendId;

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

    @Override
    public String toString() {
        return "FriendApplyDto [userId=" + userId + ", friendId=" + friendId + ", toString()=" + super.toString() + "]";
    }

}
