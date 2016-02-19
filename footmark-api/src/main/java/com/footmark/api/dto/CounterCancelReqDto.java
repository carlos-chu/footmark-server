package com.footmark.api.dto;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 计数取消Dto
 * @author carlos.chu
 * @date 2015年8月28日
 */
public class CounterCancelReqDto extends RequestDto {

    private static final long serialVersionUID = -7181752593737536190L;

    @Validatable(description = "计数的id", isNumber = true)
    private long counterId;

    public long getCounterId() {
        return counterId;
    }

    public void setCounterId(long counterId) {
        this.counterId = counterId;
    }

    @Override
    public String toString() {
        return "{\"counterId\":\"" + counterId + "\",\"toString()\":\"" + super.toString() + "\"} ";
    }

}
