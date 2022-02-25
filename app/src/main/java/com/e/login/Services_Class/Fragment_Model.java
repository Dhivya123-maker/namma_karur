package com.e.login.Services_Class;

public class Fragment_Model {
    private String image;
    private String image1;

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getText_two() {
        return text_two;
    }

    public void setText_two(String text_two) {
        this.text_two = text_two;
    }

    private String text_two;
    private String text;
    private String text_one;


    public Fragment_Model(){}

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

    public Fragment_Model(String image, String image1, String text, String text_one, String text_two) {

        this.image= image;
       this.image1 = image1;
        this.text= text;
        this.text_one= text_one;
        this.text_two = text_two;

    }}
