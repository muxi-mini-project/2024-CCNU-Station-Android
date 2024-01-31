package com.example.ccnu_station;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Achievement_Activity extends AppCompatActivity {
    ////成就模块
    private List<Achievement> data;
    ////成就模块

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

        /////成就模块
    }
}
