package com.e.login.info_Class;


public class InfoModel {
    private String image;
    private String image1;
    private String image2;
    private String image3;
    private String image4;
    private String image5;

    private String text;
    private String text_one;
    private String text_two;
    private String text_three;


    public InfoModel(){}

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

    public String getText_three() {
        return text_three;
    }

    public void setText_three(String text_three) {
        this.text_three = text_three;
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

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }



    public String getImage5() {
        return image5;
    }

    public void setImage5(String image5) {
        this.image5 = image5;
    }








    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public InfoModel(String image, String image1, String image2, String image3, String image4, String image5, String text, String text_one, String text_two, String text_three) {

        this.image= image;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.image5 = image5;
        this.text= text;
        this.text_one= text_one;
        this.text_two = text_two;
        this.text_three = text_three;

    }}