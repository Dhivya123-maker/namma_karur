package com.e.login.MarketCatClass;


public class MarketClassModel {

    String name;
    String view_count;
    String img;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public MarketClassModel(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getView_count() {
        return view_count;
    }

    public void setView_count(String view_count) {
        this.view_count = view_count;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public MarketClassModel(String name, String view_count, String img,String id) {
        this.name = name;
        this.view_count = view_count;
        this.img = img;
        this.id = id;
    }
}