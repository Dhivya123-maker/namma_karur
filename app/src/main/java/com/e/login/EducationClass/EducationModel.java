package com.e.login.EducationClass;

public class EducationModel {
    private String image;
    private  String image1;

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }


    private String text;
    private String text_one;


    public EducationModel(){}

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

    public EducationModel(String image, String image1, String text, String text_one) {

        this.image= image;
       this.image1 = image1;
        this.text= text;
        this.text_one= text_one;

    }}
