package com.hifriend.parents;

public class parent {
    public String fullname,phone,childname, childage,diagnosisAge,email,residency;

    public parent(){

    }
    public parent(String fullname,String phone, String residency, String childname,
                  String childage,String diagnosis, String email){
        this.fullname = fullname;
        this.phone =phone;
        this.residency=residency;
        this.childname=childname;
        this.childage=childage;
        this.diagnosisAge=diagnosis;
        this.email=email;

    }
}
