package com.e.login.Blog_Class;

public class Youtube_Model {
    public Youtube_Model(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl,String title,String desc) {
        this.videoUrl = videoUrl;
        this.title = title;
        this.desc = desc;
    }

    String videoUrl;
    String title;
    String desc;

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Youtube_Model(){}
}
