package com.example.ccnu_station;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Achievement_Activity extends AppCompatActivity {
    ////成就模块
    private List<Achievement> data;
    private int finished_num = 0;
    ////成就模块
//    private class AchievementHolder extends RecyclerView.ViewHolder{
//        public AchievementHolder(LayoutInflater inflater, ViewGroup parent){
//            super(inflater.inflate(R.layout.achievement_item,parent,false));
//        }
//    }

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement);

        /////成就模块

        data =Achievement.getAchivement();
        RecyclerView recyclerView = findViewById(R.id.rv);
        // 设置RecyclerView的布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 设置RecyclerView的适配器
        Achievement_Adapter achievementAdapter = new Achievement_Adapter(data);
        recyclerView.setAdapter(achievementAdapter);

        TextView finished_textview = (TextView) findViewById(R.id.finished_tv);
        TextView unfinished_textview = (TextView) findViewById(R.id.unfinished_tv);
        for (int i = 0;i < data.size() ; i++){
            if (data.get(i).isIsfinished()==true){
                finished_num+=1;
            }
        }
        finished_textview.setText(String.format("已完成：%d",finished_num));
        unfinished_textview.setText(String.format("未完成：%d",100-finished_num));


        /////成就模块


    }
}
