package com.example.ccnu_station.Record;

public class RecordResponseData {
    private Item[] notes;
    /**
     * 是否登录
     */
    private Boolean ynLogin;

    public Item[] getNotes() { return notes; }
    public void setNotes(Item[] value) { this.notes = value; }

    public Boolean getYnLogin() { return ynLogin; }
    public void setYnLogin(Boolean value) { this.ynLogin = value; }
}

