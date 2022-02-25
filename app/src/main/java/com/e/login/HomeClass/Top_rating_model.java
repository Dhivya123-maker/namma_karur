package com.e.login.HomeClass;

public class Top_rating_model {
    public Top_rating_model(String img) {
        this.img = img;
    }

    public Top_rating_model() {

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    private  String img1;

    public Top_rating_model(String img1, String text, String text1) {
        this.img1 = img1;
        this.text = text;
        this.text1 = text1;
    }

    private  String text;
    private String text1;

}
