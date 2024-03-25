package com.example.ccnu_station.Finder;


public class FinderResponseData {
    private FindItem[] treasurehuntings;
    private Boolean ynLogin;

    public FindItem[] getTreasurehuntings() { return treasurehuntings; }
    public void setTreasurehuntings(FindItem[] value) { this.treasurehuntings = value; }

    public Boolean getYnLogin() { return ynLogin; }
    public void setYnLogin(Boolean value) { this.ynLogin = value; }
}

