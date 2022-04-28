package com.e.login.JobsClass;

public class Jobs_Model {
    private  String img;


    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    String img1;

    public Jobs_Model(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public Jobs_Model(String img, String txt, String img1) {
        this.img = img;
        this.txt = txt;
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


    public Jobs_Model(){

    }

    private  String txt;
}
