package com.e.login.Gallery_Class;

public class Gallery_Model {
    public Gallery_Model(String img) {
        this.img = img;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;
    String txt;

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

    public Gallery_Model(String txt, String txt1) {
        this.txt = txt;
        this.txt1 = txt1;
    }

    String txt1;

    public Gallery_Model(){}
}
