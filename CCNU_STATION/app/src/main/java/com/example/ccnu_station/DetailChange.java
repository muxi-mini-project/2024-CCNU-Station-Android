package com.example.ccnu_station;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailChange extends AppCompatActivity {
    private String User_token;
    private CCNU_ViewModel<PersonalDetailData> viewModel;
    private PersonalDetailData Data;
    private CCNU_API api;
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
                Toast.makeText(DetailChange.this,"请求成功",Toast.LENGTH_SHORT).show();
                PersonalDetailData body = response.body();
                if(body==null)
                {
                    Toast.makeText(DetailChange.this,"响应体为空",Toast.LENGTH_SHORT).show();
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
    }
    private void updateUI(PersonalDetailData newData)
    {
    }
    public Button addDetailButton(int layoutID, LinearLayout layout, String text)
    {
        Button newButton = new Button(this);
        newButton.setText(text);
        ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        );
        params.setMargins(40,200,40,40);
        newButton.setLayoutParams(params);
        layout.addView(newButton);
        return newButton;
    }
}