package com.example.ccnu_station.Home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.ccnu_station.Achievements.Achievement_Activity;
import com.example.ccnu_station.Buidings.BuildActivity;
import com.example.ccnu_station.Call.CallActivity;
import com.example.ccnu_station.Chat.ChatPage;
import com.example.ccnu_station.Login.LoginActivity;
import com.example.ccnu_station.OutLook.SetOutLookActivity;
import com.example.ccnu_station.Personal.PersonalPage;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Record.RecordActivity;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.JsonRespond;
import com.example.ccnu_station.Reuse.SimpleData;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePage extends BaseActivity {
    public Map<String,Integer> ChatRoomMap = new HashMap<String,Integer>()
    {{
        put("HuaChat",0);
        //此处省略一堆建筑
    }};
    private ImageButton imgbtnbuild1;
    private ImageButton imgbtnbuild7;
    private ImageButton imgbtnbuildqyc;
    private ImageButton imgbtnbuildxy;
    private ImageButton imgbtnbuildsouthhall;
    private ImageButton imgbtnbuildgym;
    private ImageButton imgbtnbuildsouth;
    private ImageButton imgbtnbuildxz;
    private ImageButton btnZhaomu;
    private ImageButton btnHuaCat;
    private ImageButton btnHuaChat;
    private ImageButton btnRank;
    private ImageButton btnPersonal;
    private CCNU_API api;
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
        api = CCNU_Application.getApi();
        User_token = CCNU_Application.getUser_Token();
        btnZhaomu = findViewById(R.id.btnZhaomu);
        imgbtnbuild7 = findViewById(R.id.imgbtnbuild7);
        imgbtnbuild1 = findViewById(R.id.imgbtnbuild1);
        imgbtnbuildgym = findViewById(R.id.imgbtnbuildgym);
        imgbtnbuildsouth = findViewById(R.id.imgbtnbuildsouth);
        imgbtnbuildqyc = findViewById(R.id.imgbtnbuildqyc);
        imgbtnbuildsouthhall = findViewById(R.id.imgbtnbuildsouthhall);
        imgbtnbuildxy = findViewById(R.id.imgbtnbuildxy);
        imgbtnbuildxz = findViewById(R.id.imgbtnbuildxz);
        btnHuaCat = findViewById(R.id.btnHuaCat);
        if(!User_token.equals("null")) Updatedate();
        btnZhaomu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = Achievement_Activity.newIntent(HomePage.this);
                startActivity(intent);
                finish();
            }
        });
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
//                Intent intent = ChatPage.newIntent(HomePage.this,ChatRoomMap.get("HuaChat"));
//                startActivity(intent);
//                finish();
            }
        });
        btnRank = findViewById(R.id.btnRank);
        btnRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CallActivity.newIntent(HomePage.this);
                startActivity(intent);
                finish();
            }
        });
        btnPersonal = findViewById(R.id.btnPersonal);
        btnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!User_token.equals("null")) {
                    Intent intent = PersonalPage.newIntent(HomePage.this, CCNU_Application.getUserID());
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = LoginActivity.newIntent(HomePage.this);
                    startActivity(intent);
                    finish();
                }
            }
        });
        imgbtnbuild7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = BuildActivity.newIntent(HomePage.this,1);
                startActivity(intent);
                finish();
            }
        });
        imgbtnbuild1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = BuildActivity.newIntent(HomePage.this,2);
                startActivity(intent);
                finish();
            }
        });
        imgbtnbuildgym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = BuildActivity.newIntent(HomePage.this,3);
                startActivity(intent);
                finish();
            }
        });
        imgbtnbuildsouth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = BuildActivity.newIntent(HomePage.this,4);
                startActivity(intent);
                finish();
            }
        });
        imgbtnbuildqyc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = BuildActivity.newIntent(HomePage.this,5);
                startActivity(intent);
                finish();
            }
        });
        imgbtnbuildsouthhall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = BuildActivity.newIntent(HomePage.this,6);
                startActivity(intent);
                finish();
            }
        });
        imgbtnbuildxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = BuildActivity.newIntent(HomePage.this,7);
                startActivity(intent);
                finish();
            }
        });
        imgbtnbuildxz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = BuildActivity.newIntent(HomePage.this,8);
                startActivity(intent);
                finish();
            }
        });
    }
    private void Updatedate(){
        Call<JsonRespond<SimpleData>> updateStayDate = api.updateStayDate("Bearer "+User_token,CCNU_Application.getUserID());
        updateStayDate.enqueue(new Callback<JsonRespond<SimpleData>>() {
            @Override
            public void onResponse(Call<JsonRespond<SimpleData>> call, Response<JsonRespond<SimpleData>> response) {
            }
            @Override
            public void onFailure(Call<JsonRespond<SimpleData>> call, Throwable t) {
            }
        });
    }
}