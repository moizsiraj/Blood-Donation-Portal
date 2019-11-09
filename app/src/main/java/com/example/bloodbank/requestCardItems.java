package com.example.bloodbank;

public class requestCardItems {
    private String name;
    private String bloodBank;
    private String branch;
    private String date;
    private String time;

    public requestCardItems(String name, String bloodBank, String branch,String date,String time){
        this.name = name;
        this.bloodBank = bloodBank;
        this.branch = branch;
        this.date = date;
        this.time = time;
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
