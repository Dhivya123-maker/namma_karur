package com.e.login.EventClass;

public class EventModel{
    private String image;
    private String text;


    public EventModel(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text= text;
    }





    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }





    public EventModel(String image,String text) {

        this.image= image;
        this.text= text;



    }}
