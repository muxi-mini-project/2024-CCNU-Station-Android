package com.example.ccnu_station.Call;

public class CallResponseData {
    private CallItem[] recruits;
    private Boolean ynLogin;

    public CallItem[] getRecruits() { return recruits; }
    public void setRecruits(CallItem[] value) { this.recruits = value; }

    public Boolean getYnLogin() { return ynLogin; }
    public void setYnLogin(Boolean value) { this.ynLogin = value; }
}
