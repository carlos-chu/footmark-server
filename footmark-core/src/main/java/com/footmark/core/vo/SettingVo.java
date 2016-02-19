package com.footmark.core.vo;

import com.footmark.framework.vo.EntityVo;

/**
 * @Description: 用户设置vo
 * @author carlos.chu
 * @version 1.0 2016年2月3日 下午1:14:37
 */
public class SettingVo implements EntityVo {

    private static final long serialVersionUID = -5848213921520389422L;

    private String domain;

    private String value;

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{\"domain\":\"" + domain + "\",\"value\":\"" + value + "\"}";
    }

}
