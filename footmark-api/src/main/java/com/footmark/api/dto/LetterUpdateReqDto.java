package com.footmark.api.dto;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 更改私信阅读状态DTO
 * @author carlos.chu
 * @version 1.0 2016年1月11日 下午4:50:22
 */
public class LetterUpdateReqDto extends RequestDto {

    private static final long serialVersionUID = -3394159953552623491L;

    @Validatable(description = "私信ID", isNumber = true)
    private Long letterId;

    /**
     * 1:更改阅读状态，2：软删除
     */
    @Validatable(description = "操作标志")
    private int flag;

    public Long getLetterId() {
        return letterId;
    }

    public void setLetterId(Long letterId) {
        this.letterId = letterId;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "{\"letterId\":\"" + letterId + "\",\"flag\":\"" + flag + "\",\"toString\":\"" + super.toString()
                + "\"}";
    }

}
