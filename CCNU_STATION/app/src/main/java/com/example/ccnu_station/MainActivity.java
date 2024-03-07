package com.example.ccnu_station;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Header;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnYkLogin;
    private EditText editUsername;
    private EditText editPassword;
    private TextView textHint;
    boolean passwordCheck = false;
    boolean firstCheck = true;
    ////成就模块
    private List<Achievement> data;
    ////成就模块
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogin = (Button) findViewById(R.id.buttonLogin);
        btnYkLogin = (Button) findViewById(R.id.buttonYkLogin);
        editPassword = findViewById(R.id.EditTextKey);
        editUsername = findViewById(R.id.EditTextUsername);
        textHint = findViewById(R.id.textHint);
        btnLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String strUsername = editUsername.getText().toString();
                String strPassword = editPassword.getText().toString();
                CCNU_API api = CCNU_Application.getApi();
                //Call<Data<LoginData>> LogingCall = api.getTest();
                Call<LoginData> LogingCall = api.getLoginData(strUsername,strPassword);
                LogingCall.enqueue(new Callback<LoginData>() {
                    @Override
                    public void onResponse(Call<LoginData> call, Response<LoginData> response) {
                        Toast.makeText(MainActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                        LoginData body = response.body();

                        if(body == null) {
                            Toast.makeText(MainActivity.this,"响应体为空",Toast.LENGTH_SHORT).show();
                            return;
                        }
                        if(body.getLogin().equals("Yes")) {
                            passwordCheck = true;
                        }
                        else passwordCheck = false;
                        if(body.getFirst().equals("Yes")) {
                            firstCheck = true;
                        }
                        else firstCheck = false;
                        if(!passwordCheck) textHint.setText("用户名或密码错误！");
                        else
                        {
                            //用SharedPreferences存储token避免页面间多次传输;
                            SharedPreferences sharedPrefer = getSharedPreferences("User_Details", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPrefer.edit();
                            editor.putString("token",body.getToken());
                            editor.apply();
                            Intent intent;
                            if(firstCheck) {
                                intent = SetOutLookActivity.newIntent(MainActivity.this,strUsername);
                            }
                            else {
                                intent = HomePage.newIntent(MainActivity.this);
                            }
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginData> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnYkLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = SetOutLookActivity.newIntent(MainActivity.this,"0");
                startActivity(intent);
            }
        });
        /////成就模块

        data =Achievement.getAchivement();
        RecyclerView recyclerView = findViewById(R.id.rv);
        // 设置RecyclerView的布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        // 设置RecyclerView的适配器
        Achievement_Adapter achievementAdapter = new Achievement_Adapter(data);
        recyclerView.setAdapter(achievementAdapter);

        /////成就模块
    }

}