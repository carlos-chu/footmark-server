package com.footmark.api.dto;

import com.footmark.core.enums.MultiMediaTypeEnum;
import com.footmark.framework.annotation.Validatable;

/**
 * @Description: 多媒体的ReqDto
 * @author carlos.chu
 * @date 2015年8月21日
 */
public class MultiMediaReqDto {

    @Validatable(description = "多媒体类型", isNumber = true, enumScope = MultiMediaTypeEnum.class)
    private int type;

    private String url;

    private String outsideUrl;
    @Validatable(description = "扩展名")
    private String extName;
    @Validatable(description = "文件名，根据文件MD5")
    private String fileName;
    @Validatable(description = "文件桶名")
    private String bucket;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOutsideUrl() {
        return outsideUrl;
    }

    public void setOutsideUrl(String outsideUrl) {
        this.outsideUrl = outsideUrl;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    @Override
    public String toString() {
        return "{\"type\":\"" + type + "\",\"url\":\"" + url + "\",\"outsideUrl\":\"" + outsideUrl
                + "\",\"extName\":\"" + extName + "\",\"fileName\":\"" + fileName + "\",\"bucket\":\"" + bucket
                + "\"} ";
    }

}
