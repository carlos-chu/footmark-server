package com.footmark.api.dto;

import com.footmark.framework.annotation.Validatable;
import com.footmark.framework.dto.RequestDto;

/**
 * @Description: 回调参数
 * @author carlos.chu
 * @version 1.0 2015年12月25日 下午2:10:46
 */
public class OSSCallbackReqDto extends RequestDto {

    private static final long serialVersionUID = 5770602713959729159L;

    /**
     * 1:头像，2：脚印
     */
    private int belongType;

    @Validatable
    private Long userId;

    private Long sourceId;

    @Validatable
    private String object;

    @Validatable
    private String bucket;

    private String extName;

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public int getBelongType() {
        return belongType;
    }

    public void setBelongType(int belongType) {
        this.belongType = belongType;
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

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    @Override
    public String toString() {
        return "{\"belongType\":\"" + belongType + "\",\"userId\":\"" + userId + "\",\"sourceId\":\"" + sourceId
                + "\",\"object\":\"" + object + "\",\"bucket\":\"" + bucket + "\",\"extName\":\"" + extName + "\"}";
    }

}
