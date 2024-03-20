package com.example.ccnu_station.Achievement;

public class AchievementTotalFinishedResponse {
    private String Code;
    private Object data;
    private String msg;

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
