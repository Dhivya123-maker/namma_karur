package com.e.login.JobsClass;

public class Jobs_One_Model {
    private String txt;
    private String txt1;
    private String txt2;
    private String txt3;
    private String txt4;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Jobs_One_Model(String txt, String txt1, String txt2, String txt3, String txt4, String img, String img1, String img2, String id) {
        this.txt = txt;
        this.txt1 = txt1;
        this.txt2 = txt2;
        this.txt3 = txt3;
        this.txt4 = txt4;
        this.img = img;
        this.img1 = img1;
        this.img2 = img2;
        this.id = id;
    }

    private String img;
    private String img1;

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

    public String getTxt3() {
        return txt3;
    }

    public void setTxt3(String txt3) {
        this.txt3 = txt3;
    }

    public String getTxt4() {
        return txt4;
    }

    public void setTxt4(String txt4) {
        this.txt4 = txt4;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getImg2() {
        return img2;
    }

    public void setImg2(String img2) {
        this.img2 = img2;
    }

    public Jobs_One_Model(){}

    private String img2;

}
