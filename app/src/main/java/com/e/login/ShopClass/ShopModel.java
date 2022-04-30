package com.e.login.ShopClass;

public class ShopModel {
    private String image;
    private  String image1;
    private  String id;
    private String category;
    private String aname;
    private String aimg;
    private String ades;
    private String apri;
    private String asec;
    String mname;
    String mview_count;
    String mimg;
    String mid;

    String bname;
    String bdes;
    String bimg;
    String bid;

    public String getNum_one() {
        return num_one;
    }

    public void setNum_one(String num_one) {
        this.num_one = num_one;
    }

    String num_one;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    String num;

    public String getNid() {
        return nid;
    }

    public void setNid(String nid) {
        this.nid = nid;
    }

    String nname;

    public ShopModel(String nid) {
        this.nid = nid;
    }

    String nid;
    String nimage;



    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public String getBdes() {
        return bdes;
    }

    public void setBdes(String bdes) {
        this.bdes = bdes;
    }

    public String getBimg() {
        return bimg;
    }

    public void setBimg(String bimg) {
        this.bimg = bimg;
    }

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }


    public String getNname() {
        return this.nname;
    }

    public void setNname(String nname) {
        this.nname = nname;
    }





    public String getNimage() {
        return this.nimage;
    }

    public void setNimage(String nimage) {
        this.nimage = nimage;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMview_count() {
        return mview_count;
    }

    public void setMview_count(String mview_count) {
        this.mview_count = mview_count;
    }

    public String getMimg() {
        return mimg;
    }

    public void setMimg(String mimg) {
        this.mimg = mimg;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }



    public String getApri() {
        return apri;
    }

    public void setApri(String apri) {
        this.apri = apri;
    }

    public String getAsec() {
        return asec;
    }

    public void setAsec(String asec) {
        this.asec = asec;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage1() {
        return image1;
    }

    public void setImage1(String image1) {
        this.image1 = image1;
    }


    private String text;
    private String text_one;


    public ShopModel(){}

    public String getText_one() {
        return text_one;
    }

    public void setText_one(String text_one) {
        this.text_one = text_one;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getCategory() {
        return category;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getAimg() {
        return aimg;
    }

    public void setAimg(String aimg) {
        this.aimg = aimg;
    }

    public String getAdes() {
        return ades;
    }

    public void setAdes(String ades) {
        this.ades = ades;
    }



    public void setCategory(String category) {
        this.category = category;
    }

    public ShopModel(String image, String image1, String text, String text_one, String id, String category, String ades, String aimg, String aname, String apri,String asec
    ,String mname,String mid,String mimg,String mview_count,String bdes,String bid,String bname,String bimg,String nimage,String nname, String num, String num_one ) {

        this.image= image;
       this.image1 = image1;
        this.text= text;
        this.text_one= text_one;
        this.id = id;
        this.category = category;
        this.ades = ades;
        this.aimg = aimg;
        this.aname =  aname;
        this.asec = asec;
        this.apri = apri;
        this.mid = mid;
        this.mname = mname;
        this.mview_count = mview_count;
        this.mimg = mimg;
        this.bdes = bdes;
        this.bid =bid;
        this.bimg = bimg;
        this.bname = bname;
        this.nname = nname;
        this.nimage = nimage;
        this.num = num;
        this.num_one = num_one;




    }


}
