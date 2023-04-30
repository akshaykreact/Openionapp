package com.example.openionapp.model;

public class RecentLinkModel {

    int url_id;
    String web_link;
    String smart_link;
    String title;
    String total_clicks;
    String original_image;
    String thumbnail;
    String times_ago;
    String created_at;
    String domain_id;
    String app;

    public int getUrl_id() {
        return url_id;
    }

    public void setUrl_id(int url_id) {
        this.url_id = url_id;
    }

    public String getWeb_link() {
        return web_link;
    }

    public void setWeb_link(String web_link) {
        this.web_link = web_link;
    }

    public String getSmart_link() {
        return smart_link;
    }

    public void setSmart_link(String smart_link) {
        this.smart_link = smart_link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTotal_clicks() {
        return total_clicks;
    }

    public void setTotal_clicks(String total_clicks) {
        this.total_clicks = total_clicks;
    }

    public String getOriginal_image() {
        return original_image;
    }

    public void setOriginal_image(String original_image) {
        this.original_image = original_image;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTimes_ago() {
        return times_ago;
    }

    public void setTimes_ago(String times_ago) {
        this.times_ago = times_ago;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getDomain_id() {
        return domain_id;
    }

    public void setDomain_id(String domain_id) {
        this.domain_id = domain_id;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    @Override
    public String toString() {
        return "RecentLinkModel{" +
                "url_id=" + url_id +
                ", web_link='" + web_link + '\'' +
                ", smart_link='" + smart_link + '\'' +
                ", title='" + title + '\'' +
                ", total_clicks='" + total_clicks + '\'' +
                ", original_image='" + original_image + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", times_ago='" + times_ago + '\'' +
                ", created_at='" + created_at + '\'' +
                ", domain_id='" + domain_id + '\'' +
                ", app='" + app + '\'' +
                '}';
    }

}
