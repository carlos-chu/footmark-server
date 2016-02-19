package com.footmark.core.dao;

import com.footmark.core.entity.MultiMedia;

public interface MultiMediaMapper {
    int deleteByPrimaryKey(Long id);

    int insert(MultiMedia record);

    int insertSelective(MultiMedia record);

    MultiMedia selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(MultiMedia record);

    int updateByPrimaryKey(MultiMedia record);
}