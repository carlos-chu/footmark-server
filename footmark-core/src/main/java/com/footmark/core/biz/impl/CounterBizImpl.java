package com.footmark.core.biz.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.footmark.common.enums.ExceptionEnum;
import com.footmark.common.enums.StatusEnum;
import com.footmark.common.exception.BusinessException;
import com.footmark.core.biz.CounterBiz;
import com.footmark.core.dao.impl.CounterDaoImpl;
import com.footmark.core.entity.Counter;
import com.footmark.core.enums.CounterSourceTypeEnum;
import com.footmark.core.enums.CounterTypeEnum;
import com.footmark.core.service.impl.CounterServiceImpl;

/**
 * @Description: 计数的业务实现
 * @author carlos.chu
 * @date 2015年8月28日
 */
@Service
public class CounterBizImpl implements CounterBiz {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CounterDaoImpl counterDao;
    @Autowired
    private CounterServiceImpl counterService;

    @Override
    public long countIn(Counter counter) {
        logger.info("count in start ..., counter：{}", counter);
        if (counter.getCounterType() == CounterTypeEnum.LOVE) {
            // 1.点赞类型，是否点过赞
            Counter lovedCounter = counterService.hasCounted(counter.getUserId(), counter.getSourceId(),
                    counter.getSourceType());
            if (lovedCounter != null) {
                switch (lovedCounter.getStatus()) {
                case NORMAL: // 点过赞且没有取消过
                    throw new BusinessException(ExceptionEnum.LOVE_REPEAT_ERR);
                case DEL: // 点过赞但是取消了,重新点赞
                    counterService.updateStatus(lovedCounter.getId(), StatusEnum.NORMAL);
                    return lovedCounter.getId();
                default:
                    // ingore ...
                }
            }
        }
        // 2.创建计数
        counterDao.add(counter);
        return counter.getId();
    }

    @Override
    public void countCancel(long id) {
        Counter counter = new Counter();
        counter.setId(id);
        counter.setStatus(StatusEnum.DEL);
        counterDao.update(counter);
    }

    @Override
    public boolean isLoved(long userId, long sourceId, CounterSourceTypeEnum sourceType) {
        Counter counter = counterService.isCounting(userId, sourceId, sourceType);
        return counter != null;
    }

    @Override
    public Counter queryLiked(long userId, long sourceId, CounterSourceTypeEnum sourceType) {
        Counter counter = counterService.isCounting(userId, sourceId, sourceType);
        return counter;
    }

}
