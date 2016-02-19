package com.footmark.common.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description: Id的entity
 * @author carlos.chu
 * @date 2015年8月27日
 */
public abstract class IDEntity implements Serializable {

    private static final long serialVersionUID = 2336174195203845948L;
    protected long id;
    protected Date createTime;
    protected Date updateTime;

    public void init(IDEntity entity) {
        entity.setCreateTime(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
