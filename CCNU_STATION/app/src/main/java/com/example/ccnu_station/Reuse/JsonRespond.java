package com.example.ccnu_station.Reuse;

import com.google.gson.annotations.SerializedName;

public class JsonRespond<type> {
    private int code;
    private String msg;
    @SerializedName("data")
    private type data;
    public int getCode() {
        return code;
    }
    public String getMassage() {
        return msg;
    }
    public type getData() {
        return data;
    }
    public void setCode(int code) {
        this.code = code;
    }
    public void setData(type data) {
        this.data = data;
    }
    public void setMassage(String massage) {
        this.msg = massage;
    }
}
