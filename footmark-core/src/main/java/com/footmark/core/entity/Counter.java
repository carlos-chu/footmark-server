package com.footmark.core.entity;

import com.footmark.common.entity.Entity;
import com.footmark.core.enums.CounterSourceTypeEnum;
import com.footmark.core.enums.CounterTypeEnum;

/**
 * @Description: 计数实体
 * @author carlos.chu
 * @date 2015年8月28日
 */
public class Counter extends Entity {
    private static final long serialVersionUID = -1322031523925921209L;

    private Long userId;

    private Long sourceId;

    private CounterSourceTypeEnum sourceType;

    private CounterTypeEnum counterType;

    public Counter() {
        init(this);
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public CounterSourceTypeEnum getSourceType() {
        return sourceType;
    }

    public void setSourceType(CounterSourceTypeEnum sourceType) {
        this.sourceType = sourceType;
    }

    public CounterTypeEnum getCounterType() {
        return counterType;
    }

    public void setCounterType(CounterTypeEnum counterType) {
        this.counterType = counterType;
    }

}