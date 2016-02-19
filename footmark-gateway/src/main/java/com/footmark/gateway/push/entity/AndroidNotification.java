package com.footmark.gateway.push.entity;

import net.sf.json.JSONObject;

/**
 * @Description: 客户端设备通知体
 * @author carlos.chu
 * @version 1.0 2015年12月30日 下午5:22:04
 */
public class AndroidNotification extends PushMessage {

    private String title;

    private String description;

    private int notification_builder_id = 0;

    private int notification_basic_style = 7;

    private int open_type = 0;

    private String url;

    private String pkg_content;

    private JSONObject custom_content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNotification_builder_id() {
        return notification_builder_id;
    }

    public void setNotification_builder_id(int notification_builder_id) {
        this.notification_builder_id = notification_builder_id;
    }

    public int getNotification_basic_style() {
        return notification_basic_style;
    }

    public void setNotification_basic_style(int notification_basic_style) {
        this.notification_basic_style = notification_basic_style;
    }

    public int getOpen_type() {
        return open_type;
    }

    public void setOpen_type(int open_type) {
        this.open_type = open_type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPkg_content() {
        return pkg_content;
    }

    public void setPkg_content(String pkg_content) {
        this.pkg_content = pkg_content;
    }

    public JSONObject getCustom_content() {
        return custom_content;
    }

    public void setCustom_content(JSONObject custom_content) {
        this.custom_content = custom_content;
    }

    @Override
    public String toString() {
        return "{\"title\":\"" + title + "\",\"description\":\"" + description + "\",\"notification_builder_id\":\""
                + notification_builder_id + "\",\"notification_basic_style\":\"" + notification_basic_style
                + "\",\"open_type\":\"" + open_type + "\",\"url\":\"" + url + "\",\"pkg_content\":\"" + pkg_content
                + "\",\"custom_content\":\"" + custom_content + "\"}";
    }

}
