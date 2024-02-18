package com.example.ccnu_station;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailChange extends AppCompatActivity {
    private String User_token;
    private CCNU_ViewModel<PersonalDetailData> viewModel;
    private PersonalDetailData Data;
    private CCNU_API api;
    private ConstraintLayout backLayout;
    private TextView textNickName;
    private TextView textID;
    private TextView textCollege;
    private TextView textSex;
    private TextView textSign;
    private TextView textNote;
    private TextView textMBTI;
    private TextView textStayDate;
    private ImageButton btnNickName;
    private ImageButton btnID;
    private ImageButton btnCollege;
    private ImageButton btnSign;
    private ImageButton btnNote;
    private ImageButton btnStayDate;
    private ImageButton btnMBTI;
    private ImageButton btnSex;
    private ImageView avatar;
    public static Intent newIntent(Context packgeContext)
    {
        Intent intent = new Intent(packgeContext,DetailChange.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_change);
        viewModel = new ViewModelProvider(this).get(CCNU_ViewModel.class);
        viewModel.getData().observe(this, newData -> {
            // 数据发生变化，刷新界面
            updateUI(newData);
        });
        SharedPreferences sp = getSharedPreferences("User_Details", Context.MODE_PRIVATE);
        User_token = sp.getString("token","null");
        api = CCNU_Application.getApi();
        Call<PersonalDetailData> DetailGet = api.getPersonalDetail("Bearer "+User_token,"2023214442");
        DetailGet.enqueue(new Callback<PersonalDetailData>() {
            @Override
            public void onResponse(Call<PersonalDetailData> call, Response<PersonalDetailData> response) {
                Toast.makeText(DetailChange.this, "请求成功", Toast.LENGTH_SHORT).show();
                PersonalDetailData body = response.body();
                if (body == null) {
                    Toast.makeText(DetailChange.this, "响应体为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Data = body;
                viewModel.updateData(body);
            }
            @Override
            public void onFailure(Call<PersonalDetailData> call, Throwable t) {
                Toast.makeText(DetailChange.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });

        backLayout = findViewById(R.id.backLayout);
        btnCollege = findViewById(R.id.btnCollege);
        btnNote = findViewById(R.id.btnNote);
        btnMBTI = findViewById(R.id.btnMBTI);
        btnID = findViewById(R.id.btnID);
        btnSex = findViewById(R.id.btnSex);
        btnSign = findViewById(R.id.btnSign);
        btnNickName = findViewById(R.id.btnNickName);
        btnStayDate = findViewById(R.id.btnStayDate);
        textCollege =findViewById(R.id.textCollege);
        textNote = findViewById(R.id.textNote);
        textNickName = findViewById(R.id.textNickName);
        textID = findViewById(R.id.textID);
        textSex = findViewById(R.id.textSex);
        textSign = findViewById(R.id.textSign);
        textStayDate = findViewById(R.id.textStay_Date);
        textMBTI = findViewById(R.id.textMBTI);
        avatar = findViewById(R.id.imageviewAvatar);

        btnNickName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnMBTI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnCollege.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnStayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        btnNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
    private void updateUI(PersonalDetailData newData)
    {
        textStayDate.setText(newData.getStay_date());
        textSign.setText(newData.getSign());
        textCollege.setText(newData.getCollege());
        textNickName.setText(newData.getNickname());
        textID.setText(newData.getStuid());
        textSex.setText(newData.getGender());
        Glide.with(this)
                .load(newData.getHeadimage())
                .circleCrop()
                .into(avatar);
    }
    public Button addButton(int layoutID, LinearLayout layout, String text)
    {
        Button newButton = new Button(this);
        newButton.setText(text);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        params.setMargins(0,0,0,0);
        newButton.setLayoutParams(params);
        layout.addView(newButton);
        return newButton;
    }
}