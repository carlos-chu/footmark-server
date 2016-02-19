package com.footmark.core.vo;

import com.footmark.framework.vo.EntityVo;

/**
 * @Description: 多媒体vo
 * @author carlos.chu
 * @version 1.0 2016年2月2日 下午3:22:09
 */
public class MultiMediaVo implements EntityVo {

    private static final long serialVersionUID = 446071939081322558L;

    private Long id;

    private int type;

    private String fileName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "{\"id\":\"" + id + "\",\"type\":\"" + type + "\",\"fileName\":\"" + fileName + "\"}";
    }

}
