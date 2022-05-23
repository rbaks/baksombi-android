package com.example.baksombi.model;

import java.sql.Timestamp;

public class Token {
    public static final String PREF_TOKEN = "token";
    public static final String PREF_REFRESH = "refresh";
    private String token;
    private Timestamp expires;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Timestamp getExpires() {
        return expires;
    }

    public void setExpires(Timestamp expires) {
        this.expires = expires;
    }
}
