package com.e.login.Gallery_Class;

import android.content.Context;

import java.util.List;

public class Gallery_two_Model {
    public Gallery_two_Model(String img,String txt) {
        this.img = img;
        this.txt = txt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    private String img;
    String txt;

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Gallery_two_Model(Context applicationContext, List<Gallery_two_Model> galleryTwoModelList){}
}
