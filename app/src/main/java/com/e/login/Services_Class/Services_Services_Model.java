package com.e.login.Services_Class;

public class Services_Services_Model {
    private String image;
    private String button;
    private String text;
    private String text_one;


    public Services_Services_Model(){}

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

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Services_Services_Model(String image, String button, String text, String text_one) {

        this.image= image;
//        this.button = button;
        this.text= text;
        this.text_one= text_one;

    }}
