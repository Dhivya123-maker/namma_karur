package com.e.login.ProductsFragmentClass;

import android.widget.Button;

public class ProductsModel {
    private String image;
    private String button;
    private String text;
    private String text_one;

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public ProductsModel(String cat) {
        this.cat = cat;
    }

    String cat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public ProductsModel(){}

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

    public String getButton() {
        return button;
    }

    public void setButton(String button) {
        this.button = button;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ProductsModel(String image, String text,String text_one,String id,String cat) {

        this.image= image;
//        this.button = button;
        this.text= text;
        this.text_one= text_one;
        this.id = id;
        this.cat = cat;

    }}
