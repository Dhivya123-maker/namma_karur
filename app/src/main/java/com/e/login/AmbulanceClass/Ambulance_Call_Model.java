package com.e.login.AmbulanceClass;

public class Ambulance_Call_Model {
    private  String pri;
    private  String sec;



    public Ambulance_Call_Model() {

    }

    public String getBtn() {
        return btn;
    }

    public void setBtn(String btn) {
        this.btn = btn;
    }

    private  String btn;



    public String getSec() {
        return sec;
    }

    public void setSec(String sec) {
        this.sec = sec;
    }

    public String getPri_call() {
        return pri_call;
    }

    public void setPri_call(String pri_call) {
        this.pri_call = pri_call;
    }

    public String getSec_call() {
        return sec_call;
    }

    public void setSec_call(String sec_call) {
        this.sec_call = sec_call;
    }

    public String getPri() {
        return pri;
    }

    public void setPri(String pri) {
        this.pri = pri;
    }

    private  String pri_call;

    public Ambulance_Call_Model(String pri, String sec, String pri_call, String sec_call,String btn) {
        this.pri = pri;
        this.sec = sec;
        this.pri_call = pri_call;
        this.sec_call = sec_call;
        this.btn = btn;

    }

    private  String sec_call;
}
