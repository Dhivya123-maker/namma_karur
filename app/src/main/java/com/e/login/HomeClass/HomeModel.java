package com.e.login.HomeClass;

public class HomeModel {
    private String image;
    private String text;


    public HomeModel(){}


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public HomeModel(String image, String text) {

        this.image= image;
        this.text= text;

    }}
