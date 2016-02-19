package com.footmark.api.dto;

import java.util.List;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 用户设置Dto
 * @author carlos.chu
 * @version 1.0 2015年12月16日 下午2:44:13
 */
public class SettingReqDto extends RequestDto {

    private static final long serialVersionUID = -7668572239409169480L;

    @Validatable(description = "用户ID")
    private Long userId;
    @Validatable(description = "设置key")
    private List<Integer> keys;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Integer> getKeys() {
        return keys;
    }

    public void setKeys(List<Integer> keys) {
        this.keys = keys;
    }

    @Override
    public String toString() {
        return "{\"userId\":\"" + userId + "\",\"keys\":\"" + keys + "\"}";
    }

}
