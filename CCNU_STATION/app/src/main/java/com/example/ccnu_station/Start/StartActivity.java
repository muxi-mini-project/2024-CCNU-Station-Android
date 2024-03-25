package com.example.ccnu_station.Start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;

import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.Login.LoginActivity;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_Application;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class StartActivity extends BaseActivity {
    private static final int SPLASH_TIME_OUT=1500;
    private String User_Token = CCNU_Application.getUser_Token();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        // 延迟执行跳转到主页的操作
        executor.schedule(() -> {
            if(User_Token.equals("null")){
                Intent intent = LoginActivity.newIntent(this);
                startActivity(intent);
            }
            else{
                Intent intent = HomePage.newIntent(this);
                startActivity(intent);
            }
        }, SPLASH_TIME_OUT, TimeUnit.MILLISECONDS);
    }
}