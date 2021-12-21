package com.hifriend.Therapist;

public class Therapist {
    public String full_name, phone, email, area_of_expertise,image;

    public Therapist(){

    }

    public Therapist(String full_name, String phone, String email, String area_of_expertise,String image) {
        this.full_name = full_name;
        this.phone = phone;
        this.email = email;
        this.area_of_expertise = area_of_expertise;
        this.image=image;
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
}
