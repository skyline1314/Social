package com.skyline.social.entity;

/**
 * 活动列表结构体
 * Created by Administrator on 2017/9/18.
 */
public class ActivityEntity extends BaseEntity {

    private String title = "";
    private String time = "";
    private Object url = "";

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Object getUrl() {
        return url;
    }

    public void setUrl(Object url) {
        this.url = url;
    }
}
