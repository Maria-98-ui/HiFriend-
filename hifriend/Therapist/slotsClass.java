package com.hifriend.Therapist;

public class slotsClass {

    public String date, time;

    public slotsClass(){}

    public slotsClass(String date, String time){
        this.date=date;
        this.time = time;
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
