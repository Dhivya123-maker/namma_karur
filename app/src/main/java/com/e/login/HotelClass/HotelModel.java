package com.e.login.HotelClass;

public class HotelModel{
    private String image;
    private String text;



    public HotelModel(){}





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

    public HotelModel(String image,String text) {

        this.image= image;
        this.text= text;

    }}
