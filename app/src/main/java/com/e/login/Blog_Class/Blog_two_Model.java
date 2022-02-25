package com.e.login.Blog_Class;

public class Blog_two_Model {

    private String img;
    private String txt;
    private String txt1;

    public Blog_two_Model(String img, String txt, String txt1) {
        this.img = img;
        this.txt = txt;
        this.txt1 = txt1;
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

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public Blog_two_Model(){}
}
