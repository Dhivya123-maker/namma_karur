package com.e.login.FoodClass;

public class FoodModel{
    private String image;
    private String text;
    private String button;


    public FoodModel(){}


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


    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }



    public FoodModel(String image, String text,String button) {

        this.image= image;
        this.text= text;
        this.button= button;

    }}
