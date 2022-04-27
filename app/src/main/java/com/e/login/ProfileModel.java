package com.e.login;

public class ProfileModel {

    String year;
    String institute;
    String degree;
    String company;
    String experience_data;
    String position;
    String skills_data;
    String type;

    public ProfileModel(){}


    public ProfileModel(String year, String institute, String degree, String company, String experience_data, String position, String skills_data, String type) {
        this.year = year;
        this.institute = institute;
        this.degree = degree;
        this.company = company;
        this.experience_data = experience_data;
        this.position = position;
        this.skills_data = skills_data;
        this.type = type;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getExperience_data() {
        return experience_data;
    }

    public void setExperience_data(String experience_data) {
        this.experience_data = experience_data;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSkills_data() {
        return skills_data;
    }

    public void setSkills_data(String skills_data) {
        this.skills_data = skills_data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

