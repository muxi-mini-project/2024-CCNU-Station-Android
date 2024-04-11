package com.example.ccnu_station.Finder;

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

import com.example.ccnu_station.Buidings.BuildActivity;
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

public class FinderActivity extends BaseActivity implements FindAdapter.OnItemClickListener{
    private RecyclerView recyclerView;
    private FindAdapter adapter;
    private ArrayList<FindItem> itemList;
    private ImageButton addButton;
    private CCNU_API api;
    private String buildID;
    private ImageView background;
    private TextView buildName;
    private String user_token = CCNU_Application.getUser_Token();
    private static String Building_ID =
            "com.example.ccnu_station.FinderActivity.Building_ID";
    private ImageButton backButton;
    private SwipeRefreshLayout swipeRefreshLayout;



    public static Intent newIntent(Context packgeContext, int buildingID)
    {
        Intent intent = new Intent(packgeContext, FinderActivity.class);
        intent.putExtra(Building_ID,buildingID);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finder);
        api = CCNU_Application.getApi();
        buildID = ""+getIntent().getIntExtra(Building_ID,-1);
        background = findViewById(R.id.background);
        buildName = findViewById(R.id.textBuildName);
        buildName.setText(CCNU_Application.buildName[buildID.toCharArray()[0]-'0'-1]);
        background.setImageResource(CCNU_Application.buildBackGround[buildID.toCharArray()[0]-'0'-1]);
        recyclerView = findViewById(R.id.finderrecyclerview);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        itemList = testList();
        generateItemList(buildID); // Create a list of MyItem objects
        adapter = new FindAdapter(itemList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        addButton = findViewById(R.id.addFinds);

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
                    Intent intent = LoginActivity.newIntent(FinderActivity.this);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = AddFindActivity.newIntent(FinderActivity.this, buildID);
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

    @Override
    public void onBackPressed(){
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        Intent intent = BuildActivity.newIntent(FinderActivity.this,buildID.toCharArray()[0]-'0');
        startActivity(intent);
        finish();
    }
    public void onAvatarClick(String Personal_ID){
        Intent intent = PersonalPage.newIntent(FinderActivity.this,Personal_ID);
        startActivity(intent);
    }
    private ArrayList<FindItem> testList(){
        ArrayList<FindItem> List = new ArrayList<>();
//        FindItem item = new FindItem();
//        item.setHeadImage("https://pic.imgdb.cn/item/65e9ca429f345e8d03be51dc.jpg");
//        item.setClue("测试测试测试测试");
//        item.setImage("https://pic.imgdb.cn/item/65e9ca429f345e8d03be51dc.jpg");
//        item.setTime("2024:03:14:20:10");
//        item.setTitle("这是一个标题");
//        item.setDeadline("2024:03:14:20:10");
//        item.setThing("圣遗物");
//        for(int i = 0;i<10;i++) List.add(item);
        return List;
    }
    private void generateItemList(String buildID){
        Call<JsonRespond<FinderResponseData>> getFinds = api.getAllFindings(buildID);
        getFinds.enqueue(new Callback<JsonRespond<FinderResponseData>>() {
            @Override
            public void onResponse(Call<JsonRespond<FinderResponseData>> call, Response<JsonRespond<FinderResponseData>> response) {
                //Toast.makeText(FinderActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                JsonRespond<FinderResponseData> body = response.body();
                if(body==null) return;
                if(body.getCode()!=1000) return;
                FindItem[] items = body.getData().getTreasurehuntings();
                if(body.getCode()==2005){
                    Intent intent = LoginActivity.newIntent(FinderActivity.this);
                    startActivity(intent);
                    finishAffinity();
                }
                itemList= new ArrayList<>();
                for(int i = 0;i<items.length;i++){
                    itemList.add(items[i]);
                }
                adapter.setItemList(itemList);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<JsonRespond<FinderResponseData>> call, Throwable t) {
                Log.i("FindGet","Failed");
                //Toast.makeText(FinderActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
