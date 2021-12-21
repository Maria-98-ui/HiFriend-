package com.hifriend.SendNotifPack;

import com.google.android.gms.tasks.Task;
import com.google.firebase.installations.InstallationTokenResult;

public class Token {

    private String token;

    public Token(){}

    public Token(String token){
        this.token=token;


    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
