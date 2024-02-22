package com.example.ccnu_station.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ccnu_station.Achievement_Activity;
import com.example.ccnu_station.ChatPage;
import com.example.ccnu_station.Personal.PersonalPage;
import com.example.ccnu_station.R;

import java.util.HashMap;
import java.util.Map;

public class HomePage extends AppCompatActivity {
    public Map<String,Integer> ChatRoomMap = new HashMap<String,Integer>()
    {{
        put("HuaChat",0);
        //此处省略一堆建筑
    }};
    private Button btnZhaomu;
    private Button btnHuaCat;
    private Button btnHuaChat;
    private Button btnRank;
    private Button btnPersonal;
    private String User_token;
    /*private static final String User_Identity =
            "com.example.ccnu_station.Home.HomePage.UserIdentity";

     */
    public static Intent newIntent(Context packgeContext)
    {
        Intent intent = new Intent(packgeContext,HomePage.class);
        //intent.putExtra(User_Identity,userIdentity);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        SharedPreferences sp = getSharedPreferences("User_Details",Context.MODE_PRIVATE);
        User_token = sp.getString("token","null");
        btnZhaomu = findViewById(R.id.btnZhaomu);
        btnZhaomu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //页面跳转；
            }
        });
        btnHuaCat = findViewById(R.id.btnHuaCat);
        btnHuaCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //页面跳转
            }
        });
        btnHuaChat = findViewById(R.id.btnHuaChat);
        btnHuaChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //页面跳转
                Intent intent = ChatPage.newIntent(HomePage.this,ChatRoomMap.get("HuaChat"));
                startActivity(intent);
            }
        });
        btnRank = findViewById(R.id.btnRank);
        btnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //页面跳转
                Intent intent =new Intent(HomePage.this,Achievement_Activity.class);
                startActivity(intent);
            }
        });
        btnPersonal = findViewById(R.id.btnPersonal);
        btnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = PersonalPage.newIntent(HomePage.this,User_token);
                startActivity(intent);
            }
        });
    }
}