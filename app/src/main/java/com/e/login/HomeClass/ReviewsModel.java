package com.e.login.HomeClass;

public class ReviewsModel {
    private  String img;
    private  String txt;
    private  String txt1;


    public ReviewsModel(){}
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public String getTxt2() {
        return txt2;
    }

    public void setTxt2(String txt2) {
        this.txt2 = txt2;
    }

    public String getTxt3() {
        return txt3;
    }

    public void setTxt3(String txt3) {
        this.txt3 = txt3;
    }

    private  String txt2;

    public ReviewsModel(String img, String txt, String txt1, String txt2, String txt3) {
        this.img = img;
        this.txt = txt;
        this.txt1 = txt1;
        this.txt2 = txt2;
        this.txt3 = txt3;
    }

    private  String txt3;

}
