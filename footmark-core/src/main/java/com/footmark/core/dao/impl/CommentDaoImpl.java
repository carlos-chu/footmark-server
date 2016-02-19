package com.footmark.core.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.footmark.common.enums.StatusEnum;
import com.footmark.core.dao.CommentDao;
import com.footmark.core.entity.Comment;
import com.footmark.core.enums.CommentSourceTypeEnum;
import com.footmark.core.vo.CommentVo;
import com.footmark.framework.dao.GenericDaoDefault;

@Repository
public class CommentDaoImpl extends GenericDaoDefault<Comment> implements CommentDao {

    @Override
    public int updateStatus(Long id, StatusEnum status) {
        Comment comment = new Comment();
        comment.setId(id);
        comment.setCreateTime(null);
        comment.setStatus(status);
        return this.update(comment);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<CommentVo> query(Long sourceId, CommentSourceTypeEnum sourceType) {
        Map<String, Object> params = new HashMap<>();
        params.put("sourceId", sourceId);
        params.put("sourceType", sourceType);
        return this.query("queryDetail", params);
    }

}
