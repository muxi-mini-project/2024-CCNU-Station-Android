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

        // 设置已完成和未完成数量
        update_FinsihedTextview(data);

        /////成就模块

    }

    // 设置已完成和未完成数量
    public void update_FinsihedTextview(List<Achievement> data){
        int finished_num = 0;
        for (int i = 0;i < data.size() ; i++){
            if (data.get(i).isIsfinished()==true){
                finished_num+=1;
            }
        }
        TextView finished_textview = (TextView) findViewById(R.id.finished_tv);
        TextView unfinished_textview = (TextView) findViewById(R.id.unfinished_tv);

        finished_textview.setText(String.format("已完成：%d",finished_num));
        unfinished_textview.setText(String.format("未完成：%d",100-finished_num));

    }
}

