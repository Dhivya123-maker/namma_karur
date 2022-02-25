package com.e.login.GovtClass;

public class NGOModel{
    private String image;
    private String text;
    private String text_one;

    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public NGOModel(){}

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



    public NGOModel(String image, String text,String text_one,String id) {

        this.image= image;
        this.text= text;
        this.text_one= text_one;
        this.id = id;


    }}
