package com.e.login.TransportClass;

public class Transport_Comments_Model {
    private String img;

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    private  String img1;

    public Transport_Comments_Model(){}

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



    private String txt;
    private String txt1;

    public Transport_Comments_Model(String img, String txt, String txt1, String txt2, String img1) {
        this.img = img;
        this.txt = txt;
        this.txt1 = txt1;
        this.txt2 = txt2;
        this.img1 = img1;

    }

    private String txt2;


}
