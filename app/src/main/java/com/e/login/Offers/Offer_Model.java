package com.e.login.Offers;

public class Offer_Model {
    public Offer_Model(String img,String txt,String txt1,String cat) {
        this.img = img;
        this.txt = txt;
        this.txt1 = txt1;
        this.cat = cat;
    }

    public Offer_Model() {

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

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    String cat;

    public Offer_Model(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;
}
