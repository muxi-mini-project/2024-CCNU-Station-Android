package com.example.ccnu_station.Record;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.JsonRespond;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecordActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecordAdapter adapter;
    private ArrayList<Item> itemList;
    private int buildID;
    private static String Building_ID =
            "com.example.ccnu_station.RecordActivity.Building_ID";
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
        buildID = getIntent().getIntExtra(Building_ID,-1);
        recyclerView = findViewById(R.id.recordrecyclerview);
        //generateItemList(buildID); // Create a list of MyItem objects
        itemList = testList();
        adapter = new RecordAdapter(itemList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
    private ArrayList<Item> testList(){
        ArrayList<Item> List = new ArrayList<>();
        Item item = new Item();
        item.setAvatar("https://pic.imgdb.cn/item/65e9ca429f345e8d03be51dc.jpg");
        item.setText("测试测试测试测试");
        item.setImage1("https://pic.imgdb.cn/item/65e9ca429f345e8d03be51dc.jpg");
        item.setTime("2024:03:14:20:10");
        item.setTitle("这是一个标题");
        for(int i = 0;i<10;i++) List.add(item);
        return List;
    }
    private void generateItemList(int buildID){
        CCNU_API api = CCNU_Application.getApi();
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

            }
        });
    }
}