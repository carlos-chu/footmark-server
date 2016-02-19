package com.footmark.core.entity;

import com.footmark.common.entity.Entity;
import com.footmark.core.enums.SourceMultiTypeEnum;

/**
 * @Description: 主体与多媒体的多对多关系类
 * @author carlos.chu
 * @date 2015年8月17日
 */
public class SourceMulti extends Entity {

    /**
     * 
     */
    private static final long serialVersionUID = 2966559326267629926L;

    private Long sourceId;

    private Long multiId;

    private SourceMultiTypeEnum type;

    public SourceMulti() {
        init(this);
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Long getMultiId() {
        return multiId;
    }

    public void setMultiId(Long multiId) {
        this.multiId = multiId;
    }

    public SourceMultiTypeEnum getType() {
        return type;
    }

    public void setType(SourceMultiTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "SourceMulti [sourceId=" + sourceId + ", multiId=" + multiId + ", type=" + type + ", id=" + id
                + ", createTime=" + createTime + ", updateTime=" + updateTime + ", status=" + status + "]";
    }

}