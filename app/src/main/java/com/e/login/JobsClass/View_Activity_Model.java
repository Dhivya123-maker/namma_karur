package com.e.login.JobsClass;

public class View_Activity_Model {
 String job;
 String comp;
 String address;
 String gender;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getVacancy() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy = vacancy;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getStart_end() {
        return start_end;
    }

    public void setStart_end(String start_end) {
        this.start_end = start_end;
    }

    public String getAbt() {
        return abt;
    }

    public void setAbt(String abt) {
        this.abt = abt;
    }

    String vacancy;
 String degree;

    public View_Activity_Model(String job, String comp, String address, String gender, String vacancy, String degree, String exp, String age, String salary, String skills, String start_end, String abt) {
        this.job = job;
        this.comp = comp;
        this.address = address;
        this.gender = gender;
        this.vacancy = vacancy;
        this.degree = degree;
        this.exp = exp;
        this.age = age;
        this.salary = salary;
        this.skills = skills;
        this.start_end = start_end;
        this.abt = abt;
    }

    String exp;
 String age;
 String salary;
 String skills;
 String start_end;
 String abt;

    public View_Activity_Model(){}
}
