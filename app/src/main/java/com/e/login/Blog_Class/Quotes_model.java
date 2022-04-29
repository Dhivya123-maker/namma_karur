package com.e.login.Blog_Class;

public class Quotes_model {
    public Quotes_model(String img, String txt,String id) {
        this.img = img;
        this.txt = txt;
        this.id = id;
    }

    String img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

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
