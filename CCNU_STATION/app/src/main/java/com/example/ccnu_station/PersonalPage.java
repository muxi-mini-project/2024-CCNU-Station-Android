package com.example.ccnu_station;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalPage extends AppCompatActivity {
    private static String PersonalPage_ID =
            "com.example.ccnu_station.PersonalPage_ID";
    public static Intent newIntent(Context packgeContext, String personal_ID)
    {
        Intent intent = new Intent(packgeContext,PersonalPage.class);
        intent.putExtra(PersonalPage_ID,personal_ID);
        return intent;
    }
    private String Personal_ID;
    private TextView textName;
    private String Name;
    private TextView textID;
    private String ID;
    private TextView textSchool;
    private String School;
    private TextView textFriends;
    private String Friends;
    private TextView textFollowers;
    private String Followers;
    private TextView textStayDate;
    private String StayDate;
    private TextView textSubmitNum;
    private String SubmitNum;
    private ImageView imageHead;
    private String Head;
    private String User_token;
    private ConstraintLayout detailBlock;
    private boolean IsSelf=false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);
        textName = findViewById(R.id.textName);
        textFriends = findViewById(R.id.textfriends);
        textFollowers = findViewById(R.id.textfollowers);
        textID = findViewById(R.id.textStuid);
        textSchool = findViewById(R.id.textSchool);
        textStayDate = findViewById(R.id.textStayDate);
        textSubmitNum = findViewById(R.id.textSubmitNum);
        detailBlock = findViewById(R.id.DetailBlock);
        SharedPreferences sp = getSharedPreferences("User_Details", Context.MODE_PRIVATE);
        User_token = sp.getString("token","null");
        Personal_ID = getIntent().getStringExtra(PersonalPage_ID);
        CCNU_API api = CCNU_Application.getApi();
        Call<PersonalDetailData> DetailGet = api.getPersonalDetail("Bearer "+User_token);
        DetailGet.enqueue(new Callback<PersonalDetailData>() {
            @Override
            public void onResponse(Call<PersonalDetailData> call, Response<PersonalDetailData> response) {
                Toast.makeText(PersonalPage.this,"请求成功",Toast.LENGTH_SHORT).show();
                PersonalDetailData body = response.body();
                if(body==null)
                {
                    Toast.makeText(PersonalPage.this,"响应体为空",Toast.LENGTH_SHORT).show();
                    return;
                }


            }

            @Override
            public void onFailure(Call<PersonalDetailData> call, Throwable t) {
                Toast.makeText(PersonalPage.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}