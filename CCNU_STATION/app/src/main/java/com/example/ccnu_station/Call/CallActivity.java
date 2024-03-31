package com.example.ccnu_station.Call;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ccnu_station.Finder.FindItem;
import com.example.ccnu_station.Finder.FinderActivity;
import com.example.ccnu_station.Finder.FinderResponseData;
import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.Login.LoginActivity;
import com.example.ccnu_station.Personal.PersonalPage;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Record.RecordActivity;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.JsonRespond;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallActivity extends BaseActivity implements CallAdapter.OnItemClickListener {
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
        generateItemList(); // Create a list of MyItem objects
        adapter = new CallAdapter(itemList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        addButton = findViewById(R.id.addCall);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_token.equals("null")) {
                    Intent intent = LoginActivity.newIntent(CallActivity.this);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = AddCallActivity.newIntent(CallActivity.this);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }
    @Override
    public void onBackPressed(){
        Intent intent = HomePage.newIntent(CallActivity.this);
        startActivity(intent);
        finish();
    }
    public void onAvatarClick(String Personal_ID){
        Intent intent = PersonalPage.newIntent(CallActivity.this,Personal_ID);
        startActivity(intent);
    }
    private ArrayList<CallItem> testList(){
        ArrayList<CallItem> List = new ArrayList<>();
        CallItem item = new CallItem();
        item.setHeadimage("https://pic.imgdb.cn/item/65e9ca429f345e8d03be51dc.jpg");
        item.setTitle("标题测试");
        item.setPostTime("2024:03:14:20:10");
        item.setActivityTime("地点测试");
        item.setWhere("测试地点");
        item.setRequest("要求测试");
        for(int i = 0;i<10;i++) List.add(item);
        return List;
    }
    private void generateItemList(){
        Call<JsonRespond<CallResponseData>> getCalls = api.getAllCalls();
        getCalls.enqueue(new Callback<JsonRespond<CallResponseData>>() {
            @Override
            public void onResponse(Call<JsonRespond<CallResponseData>> call, Response<JsonRespond<CallResponseData>> response) {
                Toast.makeText(CallActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                JsonRespond<CallResponseData> body = response.body();
                if(body==null) return;
                if(body.getCode()!=1000) return;
                CallItem[] items = body.getData().getRecruits();
                itemList= new ArrayList<>();
                for(int i = 0;i<items.length;i++){
                    itemList.add(items[i]);
                }
                adapter.setItemList(itemList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<JsonRespond<CallResponseData>> call, Throwable t) {
                Log.i("FindGet","Failed");
                Toast.makeText(CallActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}