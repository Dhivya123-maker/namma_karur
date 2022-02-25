package com.e.login.NewsClass;


public class News_Breaking_Model {
    private String image;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String txt;

    public News_Breaking_Model(String id) {
        this.id = id;
    }

    String id;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }
    public News_Breaking_Model(){}

    public News_Breaking_Model(String image, String txt) {
        this.image = image;
        this.txt = txt;
    }
}
