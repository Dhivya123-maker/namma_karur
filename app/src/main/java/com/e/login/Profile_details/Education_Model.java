package com.e.login.Profile_details;

public class Education_Model {
    String ins;
    String deg;

    public Education_Model(String ins, String deg, String year) {
        this.ins = ins;
        this.deg = deg;
        this.year = year;
    }

    public String getIns() {
        return ins;
    }

    public void setIns(String ins) {
        this.ins = ins;
    }

    public String getDeg() {
        return deg;
    }

    public void setDeg(String deg) {
        this.deg = deg;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    String year;

    public Education_Model(){}

}
