package com.example.ccnu_station.Record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ccnu_station.Buidings.BuildActivity;
import com.example.ccnu_station.Call.CallActivity;
import com.example.ccnu_station.Finder.FinderActivity;
import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.Login.LoginActivity;
import com.example.ccnu_station.Personal.PersonalPage;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.JsonRespond;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordActivity extends BaseActivity implements RecordAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private RecordAdapter adapter;
    private ArrayList<Item> itemList;
    private ImageButton addButton;
    private CCNU_API api;
    private String buildID;
    private ImageView background;
    private TextView buildName;
    private String user_token = CCNU_Application.getUser_Token();
    private static String Building_ID =
            "com.example.ccnu_station.RecordActivity.Building_ID";
    private ImageButton backButton;
    private SwipeRefreshLayout swipeRefreshLayout;


    public static Intent newIntent(Context packgeContext,int buildingID)
    {
        Intent intent = new Intent(packgeContext, RecordActivity.class);
        intent.putExtra(Building_ID,buildingID);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_page);
        api = CCNU_Application.getApi();
        buildID = ""+getIntent().getIntExtra(Building_ID,-1);
        background = findViewById(R.id.background);
        background.setImageResource(CCNU_Application.buildBackGround[buildID.toCharArray()[0]-'0'-1]);
        buildName = findViewById(R.id.textBuildName);
        buildName.setText(CCNU_Application.buildName[buildID.toCharArray()[0]-'0'-1]);
        recyclerView = findViewById(R.id.Recordrecyclerview);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        itemList = testList();
        generateItemList(buildID); // Create a list of MyItem objects
        adapter = new RecordAdapter(itemList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        addButton = findViewById(R.id.addRecord);
        backButton = findViewById(R.id.backbtn);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 在这里执行刷新数据的操作
                refreshData();
            }
        });


        backButton = findViewById(R.id.backbtn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_token.equals("null")) {
                    Intent intent = LoginActivity.newIntent(RecordActivity.this);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = addRecordActivity.newIntent(RecordActivity.this, buildID);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }

    private void refreshData() {
        generateItemList(buildID);
        swipeRefreshLayout.setRefreshing(false);
    }

    public void onBackPressed(){
        Intent intent = BuildActivity.newIntent(RecordActivity.this,buildID.toCharArray()[0]-'0');
        startActivity(intent);
        finish();
    }
    public void onAvatarClick(String Personal_ID){
        Intent intent = PersonalPage.newIntent(RecordActivity.this,Personal_ID);
        startActivity(intent);
    }
    private ArrayList<Item> testList(){
        ArrayList<Item> List = new ArrayList<>();
//        Item item = new Item();
//        item.setAvatar("https://pic.imgdb.cn/item/65e9ca429f345e8d03be51dc.jpg");
//        item.setText("测试测试测试测试");
//        item.setImage1("https://pic.imgdb.cn/item/65e9ca429f345e8d03be51dc.jpg");
//        item.setTime("2024:03:14:20:10");
//        item.setTitle("这是一个标题");
//        for(int i = 0;i<10;i++) List.add(item);
        return List;
    }
    private void generateItemList(String buildID){
        Call<JsonRespond<RecordResponseData>> Datacall = api.getAllRecords(buildID);
        Datacall.enqueue(new Callback<JsonRespond<RecordResponseData>>() {
            @Override
            public void onResponse(Call<JsonRespond<RecordResponseData>> call, Response<JsonRespond<RecordResponseData>> response) {
                JsonRespond<RecordResponseData> body = response.body();
                if(body==null) return;
                if(body.getCode()!=1000) return;
                Item[] items = body.getData().getNotes();
                itemList= new ArrayList<>();
                for(int i = 0;i<items.length;i++){
                    itemList.add(items[i]);
                }
                adapter.setItemList(itemList);
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onFailure(Call<JsonRespond<RecordResponseData>> call, Throwable t) {
                Log.i("RecordGet","Failed");
            }
        });
    }
}