package com.example.ccnu_station.Call;

public class CallItem {
    private String Title;
    private String Place;
    private String TextTime;
    private String Req;
    private String Avatar;
    private String Time;

    public void setTitle(String title) {
        Title = title;
    }

    public void setAvatar(String avatar) {
        Avatar = avatar;
    }

    public void setTime(String time) {
        Time = time;
    }

    public void setPlace(String place) {
        Place = place;
    }

    public void setReq(String req) {
        Req = req;
    }

    public void setTextTime(String textTime) {
        TextTime = textTime;
    }

    public String getAvatar() {
        return Avatar;
    }

    public String getTitle() {
        return Title;
    }

    public String getTime() {
        return Time;
    }

    public String getPlace() {
        return Place;
    }

    public String getReq() {
        return Req;
    }

    public String getTextTime() {
        return TextTime;
    }
}
