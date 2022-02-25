package com.e.login.MoreInfoClass;

public class More_Info_Model {
    private  String img;

    public More_Info_Model(String img1) {
        this.img1 = img1;
    }

    private String img1;

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
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




    public More_Info_Model(){}


    public More_Info_Model(String img, String txt,String img1) {
        this.img = img;
        this.txt = txt;
        this.img1 = img1;

    }

    private  String txt;


}
