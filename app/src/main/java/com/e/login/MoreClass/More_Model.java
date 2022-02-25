package com.e.login.MoreClass;

public class More_Model {
    private String img;
    private  String txt;


    public  More_Model(){}

    public More_Model(String img, String txt) {
        this.img = img;
        this.txt = txt;
    }

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
}
