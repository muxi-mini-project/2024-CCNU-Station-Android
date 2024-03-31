package com.example.ccnu_station.Achievement;

import com.example.ccnu_station.Reuse.CCNU_API;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ccnu_station.Personal.PersonalPage;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Achievement_Activity extends AppCompatActivity {
    public static Intent newIntent;
    private static String Achievement_ID =
            "com.example.ccnu_station.Achievement_ID";
    public static Intent newIntent(Context packgeContext, String personal_ID)
    {
        Intent intent = new Intent(packgeContext, Achievement_Activity.class);
        intent.putExtra(Achievement_ID,personal_ID);
        return intent;
    }
    ////成就模块
    private List<Achievement> data;
    CCNU_API api = CCNU_Application.getApi();

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

        ////API

                                                                                 ///传入stuid
        Call<AchievementTotalFinishedResponse> AchievementCall = api.getAchievementTotalFinished("2023214438");
                                                                                ///
        AchievementCall.enqueue(new Callback<AchievementTotalFinishedResponse>() {
            @Override
            public void onResponse(Call<AchievementTotalFinishedResponse> call, Response<AchievementTotalFinishedResponse> response) {
                Toast.makeText(Achievement_Activity.this, "请求成功", Toast.LENGTH_SHORT).show();
                AchievementTotalFinishedResponse body = response.body();

                if (body == null) {
                    Toast.makeText(Achievement_Activity.this, "响应体为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String finishedData = body.getData().toString();
                    // 设置已完成和未完成数量
                    update_FinsihedTextview(finishedData);
                }
            }


            @Override
            public void onFailure(Call<AchievementTotalFinishedResponse> call, Throwable t) {
                Toast.makeText(Achievement_Activity.this, "请求失败", Toast.LENGTH_SHORT).show();
            }
        });


        ////API


    }
    public void UpdatefinsishedCheck(String UserId,String achievement_ID){
        Call<AchievementClickResponse> AchievementClickResponseCall = api.getAchievementReusult("2023214438", "1");
        AchievementClickResponseCall.enqueue(new Callback<AchievementClickResponse>()

        {
            @Override
            public void onResponse(Call<AchievementClickResponse> call, Response<AchievementClickResponse> response) {
                Toast.makeText(Achievement_Activity.this, "请求成功", Toast.LENGTH_SHORT).show();
                AchievementClickResponse body = response.body();
                if (body == null) {
                    Toast.makeText(Achievement_Activity.this, "响应体为空", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    String newfinishedString = body.getFisinishedString().toString();
                    // 设置已完成和未完成数量
                    update_FinsihedTextview(newfinishedString);
                }
            }

            @Override
            public void onFailure(Call<AchievementClickResponse> call, Throwable t) {

            }
        });

    }


    // 设置已完成和未完成数量
    public void update_FinsihedTextview(String data){
        int finished_num = 0;
        for (int i = 0;i < data.length() ; i++){
            char ch =data.charAt(i);
            if (ch == '1'){
                finished_num+=1;
            }
        }
        TextView finished_textview = (TextView) findViewById(R.id.finished_tv);
        TextView unfinished_textview = (TextView) findViewById(R.id.unfinished_tv);

        finished_textview.setText(String.format("已完成：%d",finished_num));
        unfinished_textview.setText(String.format("未完成：%d",100-finished_num));
    }
}

