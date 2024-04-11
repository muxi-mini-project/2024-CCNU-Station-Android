package com.example.ccnu_station.Buidings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ccnu_station.Call.CallActivity;
import com.example.ccnu_station.Finder.FinderActivity;
import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Record.RecordActivity;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_Application;

public class BuildActivity extends BaseActivity {
    private Button btnRecord;
    private Button btnChat;
    private Button btnFind;
    private TextView textBuildingName;
    private int buildID;
    private ImageView background;
    private TextView buildName;
    private static String Building_ID =
            "com.example.ccnu_station.BuildingActivity.Building_ID";
    public static Intent newIntent(Context packgeContext, int buildingID)
    {
        Intent intent = new Intent(packgeContext, BuildActivity.class);
        intent.putExtra(Building_ID,buildingID);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build);
        buildID = getIntent().getIntExtra(Building_ID,-1);
        btnChat = findViewById(R.id.btnChat);
        btnRecord = findViewById(R.id.btnRecord);
        btnFind = findViewById(R.id.btnFInder);
        background = findViewById(R.id.background);
        background.setImageResource(CCNU_Application.buildBackGround[buildID-1]);
        buildName = findViewById(R.id.buildName);
        buildName.setText(CCNU_Application.buildName[buildID-1]);
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = RecordActivity.newIntent(BuildActivity.this,buildID);
                startActivity(intent);
                finish();
            }
        });
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = FinderActivity.newIntent(BuildActivity.this,buildID);
                startActivity(intent);
                finish();
            }
        });
    }
    @Override
    public void onBackPressed(){
        Intent intent = HomePage.newIntent(BuildActivity.this);
        startActivity(intent);
        finish();
    }
}