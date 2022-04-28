package com.e.login.Offers;

public class Offer_One_Model {

    private String txt;
    private  String txt1;
    String cat;
    String id;
    String end_date;
    String img;


    public Offer_One_Model() {

    }

    public Offer_One_Model(String txt, String txt1, String cat, String id, String end_date, String img) {
        this.txt = txt;
        this.txt1 = txt1;
        this.cat = cat;
        this.id = id;
        this.end_date = end_date;
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

    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
