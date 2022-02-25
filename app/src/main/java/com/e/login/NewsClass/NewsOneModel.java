package com.e.login.NewsClass;

public class NewsOneModel {
    private String image;
    private String image1;
    private  String text;
    private String  text_one;
    private String Button;

    public NewsOneModel(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getText_one() {
        return text_one;
    }

    public void setText_one(String text_one) {
        this.text_one = text_one;
    }

    public String getButton() {
        return Button;
    }

    public void setButton(String button) {
        Button = button;
    }

    public NewsOneModel(){}


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


    public NewsOneModel(String image,String image1, String text,String text_one,String Button) {

        this.image= image;
        this.text = text;
        this.image1 = image1;
        this.text_one = text_one;
        this.Button = Button;



    }}
