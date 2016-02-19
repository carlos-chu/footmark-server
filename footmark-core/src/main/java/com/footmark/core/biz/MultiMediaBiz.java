package com.footmark.core.biz;

import com.footmark.core.entity.MultiMedia;

/**
 * @Description: 多媒体业务
 * @author carlos.chu
 * @version 1.0 2016年1月29日 上午10:49:31
 */
public interface MultiMediaBiz {

    /**
     * 创建多媒体
     * 
     * @param sourceId
     * @param multiMedia
     */
    void create(Long sourceId, MultiMedia multiMedia);
}
