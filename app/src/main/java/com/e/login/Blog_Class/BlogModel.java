package com.e.login.Blog_Class;

public class BlogModel {
    private String image;

    public BlogModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
    private String text;
    private String text_one;


    public BlogModel(){}

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

    public BlogModel(String image, String text, String text_one) {

        this.image= image;

        this.text= text;
        this.text_one= text_one;

    }}
