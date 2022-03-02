package com.e.login.ShopscreenClass;

public class ShopScreenModel {
    private String image;
    private  String image1;
    private String image2;
    private String image3;
    private String text;
    private String text_one;
    private String text_two;
    private  String text_three;
    private String id;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    private  String category;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }


    public String getText_four() {
        return text_four;
    }

    public void setText_four(String text_four) {
        this.text_four = text_four;
    }

    private  String text_four;

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getText_three() {
        return text_three;
    }

    public void setText_three(String text_three) {
        this.text_three = text_three;
    }



    public ShopScreenModel(){}

    public String getText_one() {
        return text_one;
    }

    public void setText_one(String text_one) {
        this.text_one = text_one;
    }



    public String getText_two() {
        return text_two;
    }

    public void setText_two(String text_two) {
        this.text_two = text_two;
    }
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ShopScreenModel(String id,String image,String image1,String image2,String image3, String text,String text_one,String text_two,String text_three,String text_four,String category) {

        this.image= image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.text_two = text_two;
        this.text= text;
        this.text_one= text_one;
        this.text_three = text_three;
        this.text_four = text_four;
        this.id = id;
        this.category = category;

    }}
