package com.example.ccnu_station.Login;

public class LoginData
{
    private boolean first;
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }
    public boolean isFirst() {
        return first;
    }
}