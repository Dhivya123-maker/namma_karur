package com.e.login.JobsClass;

public class View_MoreJob_Model {
    String img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public View_MoreJob_Model(String img, String txt,String id) {
        this.img = img;
        this.txt = txt;
        this.id = id;
    }

    String txt;

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

    public  View_MoreJob_Model(){}
}
