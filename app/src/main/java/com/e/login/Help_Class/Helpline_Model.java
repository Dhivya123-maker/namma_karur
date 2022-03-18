package com.e.login.Help_Class;

public class Helpline_Model {
    String img;
    String num;

    public Helpline_Model(){}

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Helpline_Model(String img, String txt, String num) {
        this.img = img;
        this.txt = txt;
        this.num = num;
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

    String txt;
}
