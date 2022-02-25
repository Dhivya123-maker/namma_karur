package com.e.login.HomeClass;

public class Recycler_Model {
    private  String img;
    private String img1;
    private  String txt;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
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

    public Recycler_Model(){}

    public Recycler_Model(String img, String img1, String txt, String txt1) {
        this.img = img;
        this.img1 = img1;
        this.txt = txt;
        this.txt1 = txt1;
    }

    private  String txt1;
}
