package com.example.ccnu_station.Achivement;


import java.util.ArrayList;
import java.util.List;

public class Achievement {

    public String getTitle() {
        return title;
    }


    //这里我的内容是固定的所以我用arraylist来构造一些数据
    public static List<Achievement> getAchivement() {
        List<Achievement> AchivementList = new ArrayList<>();




        // ... 添加其他任务

        return AchivementList;
    }
}