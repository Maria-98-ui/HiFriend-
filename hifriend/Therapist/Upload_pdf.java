package com.hifriend.Therapist;

public class Upload_pdf {
    public String name;
    public String url;

    public Upload_pdf(){

    }

    public Upload_pdf(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
