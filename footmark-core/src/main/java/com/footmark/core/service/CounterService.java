package com.footmark.core.service;

import com.footmark.common.enums.StatusEnum;
import com.footmark.core.entity.Counter;
import com.footmark.core.enums.CounterSourceTypeEnum;

/**
 * @Description: 计数服务
 * @author carlos.chu
 * @date 2015年8月28日
 */
public interface CounterService {

    /**
     * 正在统计中的
     * 
     * @param userId
     * @param sourceId
     * @param sourceType
     * @return
     */
    Counter isCounting(long userId, long sourceId, CounterSourceTypeEnum sourceType);

    /**
     * 统计过或者正在统计中的
     * 
     * @param userId
     * @param sourceId
     * @param sourceType
     * @return
     */
    Counter hasCounted(long userId, long sourceId, CounterSourceTypeEnum sourceType);

    /**
     * 修改点赞的状态
     * 
     * @param id
     * @param status
     */
    void updateStatus(long id, StatusEnum status);
}
