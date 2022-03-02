package com.e.login.Blog_Class;

public class Quotes_model {
    public Quotes_model(String img, String txt) {
        this.img = img;
        this.txt = txt;
    }

    String img;

    public Quotes_model(String txt1) {
        this.txt1 = txt1;
    }

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    String txt1;

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

    String txt;

    public  Quotes_model(){}
}
