package com.example.ccnu_station.Personal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.CCNU_ViewModel;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.JsonRespond;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonalPage extends BaseActivity {
    private static String PersonalPage_ID =
            "com.example.ccnu_station.PersonalPage_ID";
    public static Intent newIntent(Context packgeContext, String personal_ID)
    {
        Intent intent = new Intent(packgeContext,PersonalPage.class);
        intent.putExtra(PersonalPage_ID,personal_ID);
        return intent;
    }
    private CCNU_ViewModel<PersonalDetailData> viewModel;
    private ImageView Avatar;
    private String Personal_ID;
    private PersonalDetailData data;
    private TextView textName;
    private TextView textID;
    private TextView textSchool;
    private TextView textFriends;
    private TextView textFollowers;
    private TextView textStayDate;
    private TextView textSubmitNum;
    private String User_token=CCNU_Application.getUser_Token();
    private ConstraintLayout detailBlock;
    private Button changeButton;
    private boolean IsSelf=false;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);
        data = new PersonalDetailData();
        Avatar = findViewById(R.id.imageHead);
        textName = findViewById(R.id.textName);
        textFriends = findViewById(R.id.textfriends);
        textFollowers = findViewById(R.id.textfollowers);
        textID = findViewById(R.id.textStuid);
        textSchool = findViewById(R.id.textSchool);
        textStayDate = findViewById(R.id.textStayDate);
        textSubmitNum = findViewById(R.id.textSubmitNum);
        detailBlock = findViewById(R.id.DetailBlock);
        viewModel = new ViewModelProvider(this).get(CCNU_ViewModel.class);
        viewModel.getData().observe(this, newData -> {
            // 数据发生变化，刷新界面
            updateUI(newData);
        });
        SharedPreferences sp = getSharedPreferences("User_Details", Context.MODE_PRIVATE);
        User_token = sp.getString("token","null");
        Personal_ID = getIntent().getStringExtra(PersonalPage_ID);
        changeButton = addDetailButton();
        CCNU_API api = CCNU_Application.getApi();
        Call<JsonRespond<PersonalDetailData>> DetailGet = api.getPersonalDetail("Bearer "+User_token,"2023214442");
        DetailGet.enqueue(new Callback<JsonRespond<PersonalDetailData>>() {
            @Override
            public void onResponse(Call<JsonRespond<PersonalDetailData>> call, Response<JsonRespond<PersonalDetailData>> response) {
                Toast.makeText(PersonalPage.this,"请求成功",Toast.LENGTH_SHORT).show();
                JsonRespond<PersonalDetailData> body = response.body();
                if(body==null)
                {
                    Toast.makeText(PersonalPage.this,"响应体为空",Toast.LENGTH_SHORT).show();
                    return;
                }
                PersonalDetailData newData = body.getData();
                viewModel.updateData(newData);
            }

            @Override
            public void onFailure(Call<JsonRespond<PersonalDetailData>> call, Throwable t) {
                Toast.makeText(PersonalPage.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = DetailChange.newIntent(PersonalPage.this);
                startActivity(intent);
            }
        });
    }
    public void updateUI(PersonalDetailData newData)
    {
        String imageurl="https://pic.imgdb.cn/item/65e9ca429f345e8d03be51dc.jpg";
        if(newData.getHeadimage()!="") imageurl=newData.getHeadimage();
        Glide.with(this)
                .load("http://"+imageurl)
                .circleCrop()
                .into(Avatar);
        textName.setText(newData.getNickname());
        textFriends.setText("关注 "+newData.getFriendsNumber().toString());
        textFollowers.setText("粉丝 "+newData.getFollowerNumber().toString());
        textID.setText(newData.getStuid());
        textSchool.setText(newData.getCollege());
        textStayDate.setText("在校 "+newData.getStayDate().toString()+"天");
        textSubmitNum.setText("发布 "+newData.getPostNumber().toString());
    }
    public Button addDetailButton()
    {
        Button newButton = new Button(this);
        newButton.setText("设置");
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        params.endToEnd = R.id.DetailBlock;
        params.topToTop = R.id.DetailBlock;
        params.setMargins(40,200,130,40);
        newButton.setLayoutParams(params);
        newButton.setBackgroundColor(getResources().getColor(R.color.yellow));
        detailBlock.addView(newButton);
        return newButton;
    }
}