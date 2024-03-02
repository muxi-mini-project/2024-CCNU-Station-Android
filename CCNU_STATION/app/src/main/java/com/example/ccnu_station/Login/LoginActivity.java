package com.example.ccnu_station.Login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.Data;
import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.R;
import com.example.ccnu_station.OutLook.SetOutLookActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends BaseActivity {
    private Button btnLogin;
    private Button btnYkLogin;
    private EditText editUsername;
    private EditText editPassword;
    private TextView textHint;
    boolean passwordCheck = false;
    boolean firstCheck = true;
    public static Intent newIntent(Context packgeContext)
    {
        Intent intent = new Intent(packgeContext, LoginActivity.class);
        return intent;
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
                Call<Data> LogingCall = api.getLoginData(strUsername,strPassword);
                LogingCall.enqueue(new Callback<Data>() {
                    @Override
                    public void onResponse(Call<Data> call, Response<Data> response) {
                        Toast.makeText(LoginActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                        Data body = response.body();

                        if(body == null) {
                            Toast.makeText(LoginActivity.this,"响应体为空",Toast.LENGTH_SHORT).show();
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
                            if(firstCheck==firstCheck) {
                                intent = SetOutLookActivity.newIntent(LoginActivity.this);
                            }
                            else {
                                intent = HomePage.newIntent(LoginActivity.this);
                            }
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<Data> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        btnYkLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = SetOutLookActivity.newIntent(LoginActivity.this);
                startActivity(intent);
            }
        });
    }

}