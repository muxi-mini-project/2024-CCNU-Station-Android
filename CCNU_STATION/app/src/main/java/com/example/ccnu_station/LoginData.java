package com.example.ccnu_station;

public class LoginData
{
    private String Login;
    private String First;
    private String msg;
    private String token;

    public String getLogin() {
        return Login;
    }

    public String getFirst() {
        return First;
    }

    public String getMsg() {
        return msg;
    }

    public String getToken() {
        return token;
    }

    public void setFirst(String first) {
        First = first;
    }

    public void setLogin(String login) {
        Login = login;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setToken(String Token) {
        this.token = token;
    }
}