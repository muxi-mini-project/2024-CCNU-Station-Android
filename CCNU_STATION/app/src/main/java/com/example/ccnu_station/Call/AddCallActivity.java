package com.example.ccnu_station.Call;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.ccnu_station.Finder.AddFindActivity;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.BaseActivity;

public class AddCallActivity extends BaseActivity {
    public static Intent newIntent(Context packgeContext)
    {
        Intent intent = new Intent(packgeContext, AddCallActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call);
    }
}