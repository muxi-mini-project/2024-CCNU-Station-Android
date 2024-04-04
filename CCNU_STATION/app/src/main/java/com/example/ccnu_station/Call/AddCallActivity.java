package com.example.ccnu_station.Call;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ccnu_station.Finder.AddFindActivity;
import com.example.ccnu_station.Finder.FinderActivity;
import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.Login.LoginActivity;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Record.RecordActivity;
import com.example.ccnu_station.Record.addRecordActivity;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.JsonRespond;
import com.example.ccnu_station.Reuse.SimpleData;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCallActivity extends BaseActivity {
    private CCNU_API api=CCNU_Application.getApi();
    private String User_token=CCNU_Application.getUser_Token();
    private EditText title;
    private EditText time;
    private EditText require;
    private EditText where;
    private Button checkbtn;
    private Button cancelbtn;
    public static Intent newIntent(Context packgeContext)
    {
        Intent intent = new Intent(packgeContext, AddCallActivity.class);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_call);
        title = findViewById(R.id.edittextCallTitle);
        where = findViewById(R.id.edtCallPlace);
        time = findViewById(R.id.edittextCallTime);
        require = findViewById(R.id.edtCallReq);
        checkbtn = findViewById(R.id.buttonCheck);
        cancelbtn = findViewById(R.id.buttonCancel);
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = title.getText().toString();
                String Req = require.getText().toString();
                String Time = time.getText().toString();
                String Place = where.getText().toString();
                UploadNewCall(Title,Req,Time,Place,User_token);
            }
        });
        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CallActivity.newIntent(AddCallActivity.this);
                startActivity(intent);
                finish();
            }
        });
    }
    public void onBackPressed(){
        Intent intent = CallActivity.newIntent(AddCallActivity.this);
        startActivity(intent);
        finish();
    }
    private void UploadNewCall(String Title,String Req,String Time,String Place,String User_token){
        Call<JsonRespond<SimpleData>> postCall= api.postCall(
                "Bearer "+User_token,
                Place,
                Req,
                Title,
                Time
        );
        postCall.enqueue(new Callback<JsonRespond<SimpleData>>() {
            @Override
            public void onResponse(Call<JsonRespond<SimpleData>> call, Response<JsonRespond<SimpleData>> response) {
                //Toast.makeText(AddCallActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                JsonRespond<SimpleData> body = response.body();
                if(body.getCode()==1000){
                    Toast.makeText(AddCallActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                }
                if(body.getCode()==2005){
                    Intent intent = LoginActivity.newIntent(AddCallActivity.this);
                    startActivity(intent);
                    finishAffinity();
                }
                else{
                    //Toast.makeText(AddCallActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonRespond<SimpleData>> call, Throwable t) {
                //Toast.makeText(AddCallActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
            }
        });
    }

}