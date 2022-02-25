package com.e.login.NewsClass;


public class NewsModel {
    private String image;
    private  String txt_one;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTxt_one() {
        return txt_one;
    }

    public void setTxt_one(String txt_one) {
        this.txt_one = txt_one;
    }

    public NewsModel(String txt) {
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String txt;



    public String getImage() {
        return image;
    }




    public NewsModel(){}




    public NewsModel(String image,String txt,String id) {

        this.image= image;

        this.txt= txt;
        this.id = id;


    }}
