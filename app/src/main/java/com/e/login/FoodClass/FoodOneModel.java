package com.e.login.FoodClass;


public class FoodOneModel{
    private String image;
    private String text;
    private String text_one;


    public FoodOneModel(){}


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


    public String getText_one() {
        return text_one;
    }

    public void setText_one(String text_one) {
        this.text_one = text_one;
    }



    public FoodOneModel(String image, String text,String text_one) {

        this.image= image;
        this.text= text;
        this.text_one= text_one;

    }}
