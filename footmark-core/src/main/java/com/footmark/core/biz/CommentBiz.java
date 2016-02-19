package com.footmark.core.biz;

import java.util.List;

import com.footmark.common.enums.StatusEnum;
import com.footmark.core.entity.Comment;
import com.footmark.core.enums.CommentSourceTypeEnum;
import com.footmark.core.vo.CommentVo;

/**
 * @Description: 评论业务
 * @author carlos.chu
 * @version 1.0 2016年1月18日 下午5:32:41
 */
public interface CommentBiz {

    /**
     * 发表评论
     * 
     * @param comment
     * @return
     */
    CommentVo create(Comment comment);

    /**
     * 删除/举报评论
     * 
     * @param id
     * @param statusEnum
     */
    void update(Long id, StatusEnum statusEnum);

    /**
     * 查询评论
     * 
     * @param sourceId
     * @param sourceType
     * @return
     */
    List<CommentVo> query(Long sourceId, CommentSourceTypeEnum sourceType);
}
