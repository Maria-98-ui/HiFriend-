package com.hifriend.Therapist;

public class slotsT {
    String id, time;

    public slotsT(){

    }

    public slotsT(String id, String time){
        this.time=time;
        //this.time=time;
        this.id=id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return time;
    }
    //    public String getTime() {
//        return time;
//    }
//
//    public void setTime(String time) {
//        this.time = time;
//    }
}
