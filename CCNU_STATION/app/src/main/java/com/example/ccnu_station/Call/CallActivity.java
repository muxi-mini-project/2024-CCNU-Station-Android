package com.example.ccnu_station.Call;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.JsonRespond;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private CallAdapter adapter;
    private ArrayList<CallItem> itemList;
    private ImageButton addButton;
    private CCNU_API api;
    private String user_token = CCNU_Application.getUser_Token();
    public static Intent newIntent(Context packgeContext)
    {
        Intent intent = new Intent(packgeContext, CallActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);
        api = CCNU_Application.getApi();
        recyclerView = findViewById(R.id.Callrecyclerview);
        itemList = testList();
        //generateItemList(); // Create a list of MyItem objects
        adapter = new CallAdapter(itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        addButton = findViewById(R.id.addCall);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_token.equals("null")) {
                    Toast.makeText(CallActivity.this,"请登录",Toast.LENGTH_SHORT).show();
                }
                else {
                    Intent intent = AddCallActivity.newIntent(CallActivity.this);
                    startActivity(intent);
                }
            }
        });
    }
    private ArrayList<CallItem> testList(){
        ArrayList<CallItem> List = new ArrayList<>();
        CallItem item = new CallItem();
        item.setAvatar("https://pic.imgdb.cn/item/65e9ca429f345e8d03be51dc.jpg");
        item.setTitle("标题测试");
        item.setTime("2024:03:14:20:10");
        item.setPlace("地点测试");
        item.setTextTime("2024:03:14:20:10");
        item.setReq("要求测试");
        for(int i = 0;i<10;i++) List.add(item);
        return List;
    }
    private void generateItemList(){

    }
}