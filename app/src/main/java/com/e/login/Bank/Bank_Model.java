package com.e.login.Bank;

public class Bank_Model{
    private String image;
    private String text;


    public Bank_Model(){}



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text= text;
    }



    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }





    public Bank_Model(String image,String text) {

        this.image= image;
        this.text= text;


    }}
