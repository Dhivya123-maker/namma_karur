package com.e.login.FunClass;



public class FunModel{
    private String image;
    private String text;



    public FunModel(){}





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

    public FunModel(String image,String text) {

        this.image= image;
        this.text= text;

    }}
