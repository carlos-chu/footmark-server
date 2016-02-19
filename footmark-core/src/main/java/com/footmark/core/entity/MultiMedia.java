package com.footmark.core.entity;

import com.footmark.common.entity.Entity;
import com.footmark.core.enums.MultiMediaTypeEnum;

/**
 * @Description: 多媒体文件
 * @author carlos.chu
 * @date 2015年8月20日
 */
public class MultiMedia extends Entity {
    private static final long serialVersionUID = 5960725090969020038L;

    private MultiMediaTypeEnum type;

    private String url;

    private String outsideUrl;

    private String extName;

    private String fileName;

    private String bucket;

    public MultiMedia() {
        init(this);
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getOutsideUrl() {
        return outsideUrl;
    }

    public void setOutsideUrl(String outsideUrl) {
        this.outsideUrl = outsideUrl == null ? null : outsideUrl.trim();
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName == null ? null : extName.trim();
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket == null ? null : bucket.trim();
    }

    public MultiMediaTypeEnum getType() {
        return type;
    }

    public void setType(MultiMediaTypeEnum type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{\"type\":\"" + type + "\",\"url\":\"" + url + "\",\"outsideUrl\":\"" + outsideUrl
                + "\",\"extName\":\"" + extName + "\",\"fileName\":\"" + fileName + "\",\"bucket\":\"" + bucket
                + "\",\"id\":\"" + id + "\",\"createTime\":\"" + createTime + "\",\"status\":\"" + status + "\"} ";
    }

}