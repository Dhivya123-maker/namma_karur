package com.e.login.BusTimeClass;


public class BusTimeModel{
    private String image;
//    private String text;
    private String text_one;
    private String text_two;


    public BusTimeModel(){}

    public String getText_one() {
        return text_one;
    }

    public void setText_one(String text_one) {
        this.text_one = text_one;
    }


    public String getText_two() {
        return text_two;
    }

    public void setText_two(String text_two) {
        this.text_two = text_two;
    }




    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }



//    public String getText() {
//        return text;
//    }
//
//    public void setText(String text) {
//        this.text = text;
//    }

    public BusTimeModel(String image,String text_one,String text_two) {

        this.image= image;
//        this.text= text;
        this.text_one= text_one;
        this.text_two = text_two;

    }}
