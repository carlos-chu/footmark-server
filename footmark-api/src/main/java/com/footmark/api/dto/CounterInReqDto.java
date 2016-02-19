package com.footmark.api.dto;

import com.footmark.core.enums.CounterSourceTypeEnum;
import com.footmark.core.enums.CounterTypeEnum;
import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 写入计数DTO
 * @author carlos.chu
 * @date 2015年8月19日
 */
public class CounterInReqDto extends RequestDto {

    private static final long serialVersionUID = -7529350333519233556L;
    @Validatable(isNumber = true)
    private long userId;
    @Validatable(description = "计数服务源id", isNumber = true)
    private long sourceId;
    @Validatable(description = "计数源类型", isNumber = true, enumScope = CounterSourceTypeEnum.class)
    private int sourceType;
    @Validatable(description = "计数类型", isNumber = true, enumScope = CounterTypeEnum.class)
    private int counterType;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getSourceId() {
        return sourceId;
    }

    public void setSourceId(long sourceId) {
        this.sourceId = sourceId;
    }

    public int getSourceType() {
        return sourceType;
    }

    public void setSourceType(int sourceType) {
        this.sourceType = sourceType;
    }

    public int getCounterType() {
        return counterType;
    }

    public void setCounterType(int counterType) {
        this.counterType = counterType;
    }

    @Override
    public String toString() {
        return "{\"userId\":\"" + userId + "\",\"sourceId\":\"" + sourceId + "\",\"sourceType\":\"" + sourceType
                + "\",\"counterType\":\"" + counterType + "\",\"toString()\":\"" + super.toString() + "\"} ";
    }

}
