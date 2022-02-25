package com.e.login.chatClass;

public class Chat_Model {
    private String img;
    private String txt;

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

    private String txt1;

    public Chat_Model(String img, String txt, String txt1, String txt2) {
        this.img = img;
        this.txt = txt;
        this.txt1 = txt1;
        this.txt2 = txt2;
    }


    public  Chat_Model(){}
    private String txt2;
}
