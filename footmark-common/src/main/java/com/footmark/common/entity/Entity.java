package com.footmark.common.entity;

import com.footmark.common.enums.StatusEnum;

/**
 * @Description: model的基类
 * @author carlos.chu
 * @date 2015年8月17日
 */
public abstract class Entity extends IDEntity {

    private static final long serialVersionUID = -3370008689466822816L;

    protected StatusEnum status;

    public void init(Entity entity) {
        super.init(entity);
        entity.setStatus(StatusEnum.NORMAL);
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

}
