package com.footmark.core.biz;

import com.footmark.core.entity.Counter;
import com.footmark.core.enums.CounterSourceTypeEnum;

/**
 * @Description: 计数业务接口
 * @author carlos.chu
 * @date 2015年8月28日
 */
public interface CounterBiz {

    /**
     * 写入计数,点赞时要检查是否点过赞
     * 
     * @param counter
     * @return
     */
    long countIn(Counter counter);

    /**
     * 计数取消
     * 
     * @param id
     */
    void countCancel(long id);

    /**
     * 判断是否点过赞
     * 
     * @param userId
     * @param sourceId
     * @param sourceType
     * @return
     */
    boolean isLoved(long userId, long sourceId, CounterSourceTypeEnum sourceType);

    /**
     * 得到点赞
     * 
     * @param userId
     * @param sourceId
     * @param sourceType
     * @return
     */
    Counter queryLiked(long userId, long sourceId, CounterSourceTypeEnum sourceType);
}
