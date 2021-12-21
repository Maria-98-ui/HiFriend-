package com.hifriend.parents;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

public class modelClass_consult{

    DatabaseReference databaseReference;
    public String full_name, phone, email, area_of_expertise,image,date, time;
    //public String date, time, slot;



    public modelClass_consult(){

    }

    public modelClass_consult(String full_name, String phone, String email, String area_of_expertise,String image,String date,String time) {
        this.full_name = full_name;
        this.phone = phone;
        this.email = email;
        this.area_of_expertise = area_of_expertise;
        this.image=image;
//        this.date = date;
//        this.time=time;
    }




    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getArea_of_expertise() {
        return area_of_expertise;
    }

    public void setArea_of_expertise(String area_of_expertise) {
        this.area_of_expertise = area_of_expertise;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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



    //
//    public modelClass_consult.slots getSlots() {
//        return slots;
//    }
//
//    public void setSlots(modelClass_consult.slots slots) {
//        this.slots = slots;
//    }

//    public static class slots implements Serializable {
//
//
//        String date, time;
//
//        public slots(){}
//
//        public slots(String date, String time){
//            this.date=date;
//            this.time=time;
//
//
//        }
//
//        public String getDate() {
//            return date;
//        }
//
//        public void setDate(String date) {
//            this.date = date;
//        }
//
//        public String getTime() {
//            return time;
//        }
//
//        public void setTime(String time) {
//            this.time = time;
//        }
//    }


}

