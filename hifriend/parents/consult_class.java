package com.hifriend.parents;

public class consult_class {

    public String parent_id, parent_name, parent_Email,chils_Name, child_Diagnosis_Age,
            therapist_Name, therapist_Email,consult_Date, consult_time, parent_image, parent_residency,thera_image,parent_token;

    public consult_class(){}

    public consult_class(String parent_id, String parent_name, String parent_Email, String chils_Name, String child_Diagnosis_Age,
                         String therapist_Name, String therapist_Email, String consult_Date, String consult_time, String parent_image, String parent_residency,
                         String thera_image,String parent_token){

        this.parent_id = parent_id;
        this.parent_name = parent_name;
        this.parent_Email = parent_Email;
        this.chils_Name = chils_Name;
        this.child_Diagnosis_Age = child_Diagnosis_Age;
        this.therapist_Name = therapist_Name;
        this.therapist_Email = therapist_Email;
        this.consult_Date = consult_Date;
        this.consult_time = consult_time;
        this.parent_image=parent_image;
        this.parent_residency=parent_residency;
        this.thera_image=thera_image;
        this.parent_token=parent_token;


    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getParent_name() {
        return parent_name;
    }

    public void setParent_name(String parent_name) {
        this.parent_name = parent_name;
    }

    public String getParent_Email() {
        return parent_Email;
    }

    public void setParent_Email(String parent_Email) {
        this.parent_Email = parent_Email;
    }

    public String getChils_Name() {
        return chils_Name;
    }

    public void setChils_Name(String chils_Name) {
        this.chils_Name = chils_Name;
    }

    public String getChild_Diagnosis_Age() {
        return child_Diagnosis_Age;
    }

    public void setChild_Diagnosis_Age(String child_Diagnosis_Age) {
        this.child_Diagnosis_Age = child_Diagnosis_Age;
    }

    public String getTherapist_Name() {
        return therapist_Name;
    }

    public void setTherapist_Name(String therapist_Name) {
        this.therapist_Name = therapist_Name;
    }

    public String getTherapist_Email() {
        return therapist_Email;
    }

    public void setTherapist_Email(String therapist_Email) {
        this.therapist_Email = therapist_Email;
    }

    public String getConsult_Date() {
        return consult_Date;
    }

    public void setConsult_Date(String consult_Date) {
        this.consult_Date = consult_Date;
    }

    public String getConsult_time() {
        return consult_time;
    }

    public void setConsult_time(String consult_time) {
        this.consult_time = consult_time;
    }

    public String getParent_image() {
        return parent_image;
    }

    public void setParent_image(String parent_image) {
        this.parent_image = parent_image;
    }

    public String getParent_residency() {
        return parent_residency;
    }

    public void setParent_residency(String parent_residency) {
        this.parent_residency = parent_residency;
    }

    public String getThera_image() {
        return thera_image;
    }

    public void setThera_image(String thera_image) {
        this.thera_image = thera_image;
    }

    public String getParent_token() {
        return parent_token;
    }

    public void setParent_token(String parent_token) {
        this.parent_token = parent_token;
    }
}

