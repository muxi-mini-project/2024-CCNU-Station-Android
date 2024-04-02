package com.example.ccnu_station.Achievement;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.JsonRespond;
import com.example.ccnu_station.Reuse.SimpleData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Achievement_Activity extends AppCompatActivity implements Achievement_Adapter.OnItemClickListener{

    public static Intent newIntent;
    private static String Achievement_ID =
            "com.example.ccnu_station.Achievement_ID";
    private CCNU_API api;
    private RecyclerView recyclerView;
    private TextView finished_textview;
    private TextView unfinished_textview;
    private String User_token = CCNU_Application.getUser_Token();
    public static Intent newIntent(Context packgeContext, String personal_ID)
    {
        Intent intent = new Intent(packgeContext, Achievement_Activity.class);
        intent.putExtra(Achievement_ID,personal_ID);
        return intent;
    }
    private List<Achievement> data;
    @Override
    public void onBackPressed(){
        Intent intent = HomePage.newIntent(Achievement_Activity.this);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.achievement);

        data =Achievement.getAchivement();
        recyclerView = findViewById(R.id.rv);
        // 设置RecyclerView的布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 设置RecyclerView的适配器
        Achievement_Adapter achievementAdapter = new Achievement_Adapter(data,Achievement_Activity.this);
        recyclerView.setAdapter(achievementAdapter);
        finished_textview = (TextView) findViewById(R.id.finished_tv);
        unfinished_textview = (TextView) findViewById(R.id.unfinished_tv);

        ////API
        api = CCNU_Application.getApi();

        ////API


    }

    @Override
    public void onAchieveClick(String personal_id, Integer achID) {
        clickcheckbox(achID);
        if (achID >= 0 && achID < data.size()) {
            // 获取指定索引的成就对象
            Achievement achievementToChange = data.get(achID);

            // 修改成就的完成状态为true
            achievementToChange.setIsfinished(!data.get(achID).isIsfinished());
        }
    }

//                    data.get(achID).getAchievementId(),
//                data.get(achID).getTitle(),
//                data.get(achID).setIsfinished(data.get(achID).isIsfinished()==false)

    //////?计划实现当点击checkbox后 根据achID将完成状况改变 获得一串“1”“0”组成的长度为100的字符串用以更新成就完成状况?
    private void clickcheckbox(int achID){
        Call<JsonRespond<SimpleData>> clickCheckboxCall = api.getAchievementReusult("Bearer "+User_token,CCNU_Application.getUserID(),Integer.toString(achID));

        clickCheckboxCall.enqueue(new Callback<JsonRespond<SimpleData>>() {
            @Override
            public void onResponse(Call<JsonRespond<SimpleData>> call, Response<JsonRespond<SimpleData>> response) {
                Toast.makeText(Achievement_Activity.this,"请求成功",Toast.LENGTH_SHORT).show();
                JsonRespond<SimpleData> body =response.body();

                if(body == null) {
                    Toast.makeText(Achievement_Activity.this,"响应体为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(body.getCode()==1000){
                    Toast.makeText(Achievement_Activity.this,"更改成功",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonRespond<SimpleData>> call, Throwable t) {

            }
        });
    }

    ////?计划是实现当从home跳转的到成就界面时根据用户的Id设置对应的成就列表的完成情况?

    private void setAchievementList() {
        Call<JsonRespond<SimpleData>> AchievementCall = api.getAchievementTotalFinished("Bearer "+User_token,CCNU_Application.getUserID());
        ///
        AchievementCall.enqueue(new Callback<JsonRespond<SimpleData>>() {
            @Override
            public void onResponse(Call<JsonRespond<SimpleData>> call, Response<JsonRespond<SimpleData>> response) {
                Toast.makeText(Achievement_Activity.this,"请求成功",Toast.LENGTH_SHORT).show();
                JsonRespond<SimpleData> body =response.body();
                if(body == null) {
                    Toast.makeText(Achievement_Activity.this,"响应体为空",Toast.LENGTH_SHORT).show();
                    return;
                }else{
                    String finishedData =body.getData().toString();
                    // 设置已完成和未完成数量
                    update_FinsihedTextview(finishedData);
                }
            }

            @Override
            public void onFailure(Call<JsonRespond<SimpleData>> call, Throwable t) {
                Toast.makeText(Achievement_Activity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

    // 设置已完成和未完成数量
    public void update_FinsihedTextview (String finishedString){
        int finished_num = 0;
        for (int i = 0;i < finishedString.length() ; i++){
            char ch =finishedString.charAt(i);
            if (ch == '1'){
                finished_num+=1;
            }
        }
        finished_textview.setText(String.format("已完成：%d",finished_num));
        unfinished_textview.setText(String.format("未完成：%d",100-finished_num));

    }
}

