package com.e.login.Blog_Class;

public class Quotes_Catalog_Model {
    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Quotes_Catalog_Model(String img) {
        this.img = img;
    }

    String img;
    String catalog_id;

    public Quotes_Catalog_Model(String catalog_id, String gallery_cat) {
        this.catalog_id = catalog_id;
        this.gallery_cat = gallery_cat;
    }

    public String getCatalog_id() {
        return catalog_id;
    }

    public void setCatalog_id(String catalog_id) {
        this.catalog_id = catalog_id;
    }

    public String getGallery_cat() {
        return gallery_cat;
    }

    public void setGallery_cat(String gallery_cat) {
        this.gallery_cat = gallery_cat;
    }

    String gallery_cat;


    public Quotes_Catalog_Model(){}
}
