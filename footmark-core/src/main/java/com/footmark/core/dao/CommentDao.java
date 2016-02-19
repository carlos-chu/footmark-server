package com.footmark.core.dao;

import java.util.List;

import com.footmark.common.enums.StatusEnum;
import com.footmark.core.enums.CommentSourceTypeEnum;
import com.footmark.core.vo.CommentVo;

public interface CommentDao {

    int updateStatus(Long id, StatusEnum status);

    List<CommentVo> query(Long sourceId, CommentSourceTypeEnum sourceType);

}