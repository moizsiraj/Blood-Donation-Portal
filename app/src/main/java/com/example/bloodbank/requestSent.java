package com.example.bloodbank;

import androidx.annotation.NonNull;

public class requestSent {

    private String noOfDonor;
    private String bloodgrp;
    private String bloodbank;
    private String location;
    private String name;
    private String date;
    private String time;
    private String key;
    private String phone;

    public requestSent(String noOfDonor, String bloodgrp, String bloodbank, String branch, String date, String time, String key, String name, String phone) {
        this.phone = phone;
        this.noOfDonor = noOfDonor;
        this.bloodgrp = bloodgrp;
        this.bloodbank = bloodbank;
        this.location = branch;
        this.date = date;
        this.time = time;
        this.key = key;
        this.name = name;
    }

    public requestSent() {
    }

    @Override
    public String toString() {
        return "requestSent{" +
                "noOfDonor='" + noOfDonor + '\'' +
                ", bloodgrp='" + bloodgrp + '\'' +
                ", bloodbank='" + bloodbank + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", key='" + key + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getNoOfDonor() {
        return noOfDonor;
    }

    public void setNoOfDonor(String noOfDonor) {
        this.noOfDonor = noOfDonor;
    }

    public String getBloodbank() {
        return bloodbank;
    }

    public void setBloodbank(String bloodbank) {
        this.bloodbank = bloodbank;
    }

    public String getBloodgrp() {
        return bloodgrp;
    }

    public void setBloodgrp(String bloodgrp) {
        this.bloodgrp = bloodgrp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
