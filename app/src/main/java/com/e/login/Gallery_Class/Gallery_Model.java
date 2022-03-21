package com.e.login.Gallery_Class;

public class Gallery_Model {

    String txt1;
    String img1;
    String txt;
    String imd2;
    String imd3;
    String imd4;

    public Gallery_Model(){}


    public Gallery_Model(String txt1, String img1, String txt, String imd2, String imd3, String imd4) {
        this.txt1 = txt1;
        this.img1 = img1;
        this.txt = txt;
        this.imd2 = imd2;
        this.imd3 = imd3;
        this.imd4 = imd4;
    }

    public String getTxt1() {
        return txt1;
    }

    public void setTxt1(String txt1) {
        this.txt1 = txt1;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getImd2() {
        return imd2;
    }

    public void setImd2(String imd2) {
        this.imd2 = imd2;
    }

    public String getImd3() {
        return imd3;
    }

    public void setImd3(String imd3) {
        this.imd3 = imd3;
    }

    public String getImd4() {
        return imd4;
    }

    public void setImd4(String imd4) {
        this.imd4 = imd4;
    }
}
