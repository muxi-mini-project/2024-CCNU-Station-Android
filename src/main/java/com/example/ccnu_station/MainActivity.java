package com.example.ccnu_station;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnYkLogin;
    private EditText editUsername;
    private EditText editPassword;
    private TextView textHint;
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
                boolean passwordCheck=true;
                //此处省略一个接口
                if(!passwordCheck) textHint.setText("用户名或密码错误！");
                else
                {
                    Intent intent = SetOutLookActivity.newIntent(MainActivity.this,strUsername);
                    startActivity(intent);
                }
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
    }
}