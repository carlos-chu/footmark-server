package com.footmark.core.service;

import java.util.List;

import com.footmark.core.enums.SourceMultiTypeEnum;
import com.footmark.core.vo.MultiMediaVo;

/**
 * @Description: 多媒体服务
 * @author carlos.chu
 * @date 2015年8月20日
 */
public interface MultiMediaService {

    /**
     * 根据sourceMulti查询多媒体
     * 
     * @param sourceId
     * @param sourceType
     * @return
     */
    List<MultiMediaVo> queryBySource(long sourceId, SourceMultiTypeEnum sourceType);
}
