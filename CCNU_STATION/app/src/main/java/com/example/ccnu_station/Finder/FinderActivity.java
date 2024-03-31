package com.example.ccnu_station.Finder;

import androidx.appcompat.app.AppCompatActivity;
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

import com.example.ccnu_station.Buidings.BuildActivity;
import com.example.ccnu_station.Call.CallActivity;
import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.Login.LoginActivity;
import com.example.ccnu_station.Personal.PersonalPage;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Record.Item;
import com.example.ccnu_station.Record.RecordActivity;
import com.example.ccnu_station.Record.addRecordActivity;
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
    private String user_token = CCNU_Application.getUser_Token();
    private static String Building_ID =
            "com.example.ccnu_station.FinderActivity.Building_ID";
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
        background.setImageResource(CCNU_Application.buildBackGround[buildID.toCharArray()[0]-'0'-1]);
        recyclerView = findViewById(R.id.finderrecyclerview);
        itemList = testList();
        generateItemList(buildID); // Create a list of MyItem objects
        adapter = new FindAdapter(itemList,this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        addButton = findViewById(R.id.addFinds);
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
    public void onBackPressed(){
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
        FindItem item = new FindItem();
        item.setHeadImage("https://pic.imgdb.cn/item/65e9ca429f345e8d03be51dc.jpg");
        item.setClue("测试测试测试测试");
        item.setImage("https://pic.imgdb.cn/item/65e9ca429f345e8d03be51dc.jpg");
        item.setTime("2024:03:14:20:10");
        item.setTitle("这是一个标题");
        item.setDeadline("2024:03:14:20:10");
        item.setThing("圣遗物");
        for(int i = 0;i<10;i++) List.add(item);
        return List;
    }
    private void generateItemList(String buildID){
        Call<JsonRespond<FinderResponseData>> getFinds = api.getAllFindings(buildID);
        getFinds.enqueue(new Callback<JsonRespond<FinderResponseData>>() {
            @Override
            public void onResponse(Call<JsonRespond<FinderResponseData>> call, Response<JsonRespond<FinderResponseData>> response) {
                Toast.makeText(FinderActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                JsonRespond<FinderResponseData> body = response.body();
                if(body==null) return;
                if(body.getCode()!=1000) return;
                FindItem[] items = body.getData().getTreasurehuntings();
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
                Toast.makeText(FinderActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}