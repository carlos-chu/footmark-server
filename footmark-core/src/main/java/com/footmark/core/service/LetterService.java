package com.footmark.core.service;

import java.util.List;

import com.footmark.core.enums.ReadStatusEnum;
import com.footmark.core.vo.LetterVo;

/**
 * @Description: 私信服务
 * @author carlos.chu
 * @version 1.0 2016年1月11日 下午4:15:31
 */
public interface LetterService {

    /**
     * 查询私信
     * 
     * @param uid
     * @param readStatus
     * @return
     */
    List<LetterVo> query(Long uid, ReadStatusEnum readStatus);

    /**
     * 查询私信详情，包括回复详情
     * 
     * @param letterId
     * @return
     */
    List<LetterVo> queryDetail(Long letterId);
}
