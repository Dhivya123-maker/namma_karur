package com.e.login.TransportClass;

public class TransportModel {
    private String image;
    private  String image1;

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }


    private String text;
    private String text_one;


    public TransportModel(){}

    public String getText_one() {
        return text_one;
    }

    public void setText_one(String text_one) {
        this.text_one = text_one;
    }


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

    public TransportModel(String image, String image1, String text, String text_one) {

        this.image= image;
       this.image1 = image1;
        this.text= text;
        this.text_one= text_one;

    }}
