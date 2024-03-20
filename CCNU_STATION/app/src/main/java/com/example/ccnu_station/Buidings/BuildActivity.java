package com.example.ccnu_station.Buidings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ccnu_station.R;
import com.example.ccnu_station.Record.RecordActivity;

public class BuildActivity extends AppCompatActivity {
    private Button btnRecord;
    private Button btnChat;
    private Button btnFind;
    private TextView textBuildingName;
    private String backgroundSrc;
    private int buildID;
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
        btnRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = RecordActivity.newIntent(BuildActivity.this,buildID);
                startActivity(intent);
            }
        });
    }
}