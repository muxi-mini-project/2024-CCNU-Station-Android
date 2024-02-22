package com.example.ccnu_station.Record;

public class RecordData {
    private String key1=null;
    private String key2=null;
    private String key3=null;
    private String key4=null;
    private String key5=null;
    private int keyNum=0;
    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public void setKey2(String key2) {
        this.key2 = key2;
    }

    public void setKey3(String key3) {
        this.key3 = key3;
    }

    public void setKey4(String key4) {
        this.key4 = key4;
    }

    public void setKey5(String key5) {
        this.key5 = key5;
    }
    public void setKey(String key)
    {
        keyNum++;
        if(key1==null) key1=key;
        else if(key2==null) key2=key;
        else if(key3==null) key3=key;
        else if(key4==null) key4=key;
        else if(key5==null) key5=key;
    }

    public String getKey1() {
        return key1;
    }

    public String getKey2() {
        return key2;
    }

    public String getKey3() {
        return key3;
    }

    public String getKey4() {
        return key4;
    }

    public String getKey5() {
        return key5;
    }
}
