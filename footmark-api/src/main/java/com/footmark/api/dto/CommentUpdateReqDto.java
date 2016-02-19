package com.footmark.api.dto;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 更改评论状态DTO
 * @author carlos.chu
 * @version 1.0 2016年1月11日 下午4:50:22
 */
public class CommentUpdateReqDto extends RequestDto {

    private static final long serialVersionUID = -3394159953552623491L;

    @Validatable(description = "评论ID", isNumber = true)
    private Long commentId;

    /**
     * 1:杀出，2：举报
     */
    @Validatable(description = "操作标志")
    private int status;

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "{\"commentId\":\"" + commentId + "\",\"flag\":\"" + status + "\",\"toString\":\"" + super.toString()
                + "\"}";
    }

}
