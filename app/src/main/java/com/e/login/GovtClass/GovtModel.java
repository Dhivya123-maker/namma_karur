package com.e.login.GovtClass;

public class GovtModel{
    private String image;
    private String text;
    String id;


    public GovtModel(){}



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public GovtModel(String image, String text,String id) {

        this.image= image;
        this.text= text;
        this.id = id;


    }}
