package com.e.login.Non_govt_Class;

public class Non_govt_model{
    private String image;
    private String text;


    public Non_govt_model(){}



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

    public Non_govt_model(String image, String text) {

        this.image= image;
        this.text= text;


    }}
