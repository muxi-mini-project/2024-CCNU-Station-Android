package com.example.ccnu_station;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SetOutLookActivity extends AppCompatActivity {
    private static final String User_Identity =
            "com.example.ccnu_station.SetOutLookActivity.UserIdentity";
    private String User;
    private TextView textTest;
    public static Intent newIntent(Context packgeContext, String userIdentity)
    {
        Intent intent = new Intent(packgeContext, SetOutLookActivity.class);
        intent.putExtra(User_Identity,userIdentity);
        return intent;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_out_look);
        User = getIntent().getStringExtra(User_Identity);
        User = getIntent().getStringExtra(User_Identity);
        textTest = (TextView) findViewById(R.id.textTest);
        textTest.setText(User);
    }

}