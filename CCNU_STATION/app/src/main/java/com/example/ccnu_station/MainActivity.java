package com.example.ccnu_station;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnYkLogin;
    private EditText editUsername;
    private EditText editPassword;
    private TextView textHint;
    boolean passwordCheck = true;
    boolean firstCheck = true;
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
                Call<Data<LoginData>> LogingCall = api.getLoginData(strUsername,strPassword);
                LogingCall.enqueue(new Callback<Data<LoginData>>() {
                    @Override
                    public void onResponse(Call<Data<LoginData>> call, Response<Data<LoginData>> response) {
                        Toast.makeText(MainActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                        Data<LoginData> body = response.body();
                        if(body == null) return;
                        LoginData dataString = body.getData();
                        if(dataString == null) return;
                        if(dataString.getLogin()=="Yes") passwordCheck = true;
                        else passwordCheck = false;
                        if(dataString.getFirst()=="Yes") firstCheck = true;
                        else firstCheck = false;
                    }

                    @Override
                    public void onFailure(Call<Data<LoginData>> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"请求失败",Toast.LENGTH_SHORT).show();
                    }
                });
                if(!passwordCheck) textHint.setText("用户名或密码错误！");
                else
                {
                    Intent intent;
                    if(firstCheck) {
                        intent = SetOutLookActivity.newIntent(MainActivity.this,strUsername);
                    }
                    else {
                        intent = HomePage.newIntent(MainActivity.this,strUsername);
                    }
                    startActivity(intent);
                }
            }
        });
        btnYkLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = HomePage.newIntent(MainActivity.this,"0");
                startActivity(intent);
            }
        });
    }

}