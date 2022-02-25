package com.e.login.Offers;

public class Offer_One_Model {
    public Offer_One_Model(String img,String txt,String txt1) {
        this.img = img;
        this.txt = txt;
        this.txt1 = txt1;
    }

    public Offer_One_Model() {

    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;

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

    private String txt;
    private  String txt1;
}
