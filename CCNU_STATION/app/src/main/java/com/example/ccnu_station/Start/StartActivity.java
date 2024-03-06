package com.example.ccnu_station.Start;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.Login.LoginActivity;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_Application;

public class StartActivity extends BaseActivity {
    private String User_Token = CCNU_Application.getUser_Token();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        if(User_Token=="null"){
            Intent intent = LoginActivity.newIntent(this);
            startActivity(intent);
        }
        else{
            Intent intent = HomePage.newIntent(this);
            startActivity(intent);
        }
    }
}