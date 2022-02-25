package com.e.login.BloodClass;

public class Blood_Model {
    String name;
    String posted;
    String p_name;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Blood_Model(String img) {
        this.img = img;
    }

    String img;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosted() {
        return posted;
    }

    public void setPosted(String posted) {
        this.posted = posted;
    }

    public String getP_name() {
        return p_name;
    }

    public void setP_name(String p_name) {
        this.p_name = p_name;
    }

    public String getB_grp() {
        return b_grp;
    }

    public void setB_grp(String b_grp) {
        this.b_grp = b_grp;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getNeed() {
        return need;
    }

    public void setNeed(String need) {
        this.need = need;
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getC_num() {
        return c_num;
    }

    public void setC_num(String c_num) {
        this.c_num = c_num;
    }

    public String getA_num() {
        return a_num;
    }

    public void setA_num(String a_num) {
        this.a_num = a_num;
    }


    public Blood_Model(){}

    String b_grp;
    String problem;
    String need;

    public Blood_Model(String name, String posted, String p_name, String b_grp, String problem, String need, String units, String hospital, String address, String c_num, String a_num) {
        this.name = name;
        this.posted = posted;
        this.p_name = p_name;
        this.b_grp = b_grp;
        this.problem = problem;
        this.need = need;
        this.units = units;
        this.hospital = hospital;
        this.address = address;
        this.c_num = c_num;
        this.a_num = a_num;
    }

    String units;
    String hospital;
    String address;
    String c_num;
    String a_num;
}