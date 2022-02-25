package com.e.login.SmallBusClass;

public class SmallBusModel{
    private String image;
    private String text;



    public SmallBusModel(){}





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

    public SmallBusModel(String image,String text) {

        this.image= image;
        this.text= text;

    }}
