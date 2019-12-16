package com.example.bloodbank;

public class requestCardItems {
    private String name;
    private String bloodBank;
    private String branch;
    private String date;
    private String time;
    private String phone;
    private String key;

    public requestCardItems(String name, String bloodBank, String branch,String date,String time, String phone, String key){
        this.name = name;
        this.bloodBank = bloodBank;
        this.branch = branch;
        this.date = date;
        this.time = time;
        this.phone = phone;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBloodBank() {
        return bloodBank;
    }

    public String getBranch() {
        return branch;
    }

    public String getName() {
        return name;
    }


    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
