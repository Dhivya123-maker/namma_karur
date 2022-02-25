package com.e.login.GovtClass;

public class Government_Model {
    private  String img;

    public Government_Model(String img, String txt) {
        this.img = img;
        this.txt = txt;
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

    public  Government_Model(){}

    private String txt;

}
