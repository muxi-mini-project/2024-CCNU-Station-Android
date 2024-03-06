package com.example.ccnu_station.Record;

import java.util.ArrayList;

public class RecordData {
    private ArrayList<String> key;
    private int KeyNum;
    public RecordData(){
        ArrayList<String> key = new ArrayList<>();
        KeyNum=0;
    }

    public int getKeyNum() {
        return KeyNum;
    }
    public void addKeyNum()
    {
        KeyNum++;
    }
    public void addKey(String key)
    {
        this.key.add(key);
    }
}
