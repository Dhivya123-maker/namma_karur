package com.e.login.JobSearchClass;

public class Job_Search_one_Model {
    private  String img;

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

    public String getSwitch_button() {
        return switch_button;
    }

    public void setSwitch_button(String switch_button) {
        this.switch_button = switch_button;
    }

    private  String txt;

    public Job_Search_one_Model(String img, String txt, String switch_button) {
        this.img = img;
        this.txt = txt;
        this.switch_button = switch_button;
    }

    public Job_Search_one_Model(){}

    private String switch_button;
}
