package com.example.bloodbank;

public class user {

    String name;
    String phone;
    String dob;
    String gender;
    String bloodGrp;
    String location;

    public user(String name, String phone, String dob, String gender, String bloodGrp, String location) {
        this.name = name;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.bloodGrp = bloodGrp;
        this.location = location;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setBloodGrp(String bloodGrp) {
        this.bloodGrp = bloodGrp;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getDob() {
        return dob;
    }

    public String getGender() {
        return gender;
    }

    public String getBloodGrp() {
        return bloodGrp;
    }

    public String getLocation() {
        return location;
    }
}
