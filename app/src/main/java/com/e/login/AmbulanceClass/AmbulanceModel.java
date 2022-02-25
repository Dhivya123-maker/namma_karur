package com.e.login.AmbulanceClass;

public class AmbulanceModel{
    private String image;
    private String text;
    private String text_one;
    private String btn;
    private  String btn_one;


    public AmbulanceModel(){}

    public String getText_one() {
        return text_one;
    }

    public void setText_one(String text_one) {
        this.text_one = text_one;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text= text;
    }


    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn= btn;
    }


    public String getBtn_one() {
        return btn_one;
    }

    public void setBtn_one(String btn_one) {
        this.btn_one= btn_one;
    }





    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }





    public AmbulanceModel(String image,String text_one,String text,String btn, String btn_one) {

        this.image= image;
       this.text= text;
        this.text_one= text_one;
        this.btn = btn;
        this.btn_one = btn_one;


    }}
