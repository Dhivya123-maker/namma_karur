package com.e.login.HomeClass;

public class CategoryModel {

    String img;
    String name;
    String id;
    String cat_name;
    String url;

    public  CategoryModel(){}

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public CategoryModel(String img, String name, String id, String cat_name,String url) {
        this.img = img;
        this.name = name;
        this.id = id;
        this.cat_name = cat_name;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
