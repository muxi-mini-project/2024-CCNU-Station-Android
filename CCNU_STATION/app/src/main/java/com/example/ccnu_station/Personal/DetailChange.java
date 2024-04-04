package com.example.ccnu_station.Personal;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ccnu_station.Finder.AddFindActivity;
import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.OutLook.SetOutLookActivity;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.CCNU_ViewModel;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.FileUtil;
import com.example.ccnu_station.Reuse.JsonRespond;
import com.example.ccnu_station.Reuse.QnTokenJson;
import com.example.ccnu_station.Reuse.SimpleData;
import com.example.ccnu_station.Start.StartActivity;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.utils.Json;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailChange extends BaseActivity {
    private String User_token;
    private CCNU_ViewModel<PersonalDetailData> viewModel;
    private PersonalDetailData Data;
    private CCNU_API api;
    private Button SignOutBtn;
    private EditText textNickName;
    private TextView textID;
    private TextView textCollege;
    private EditText textNote;
    private EditText textMBTI;
    private Button checkbtn;
    private ImageView avatar;
    private TextView textGender;
    private NumberPicker yearPicker;
    private NumberPicker monthPicker;
    private NumberPicker dayPicker;
    private int year=2000;
    private int month=1;
    private int day=1;
    public static Intent newIntent(Context packgeContext)
    {
        Intent intent = new Intent(packgeContext,DetailChange.class);
        return intent;
    }
    private ActivityResultLauncher<String> getContentLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    File avatarFile;
                    Uri UriImage = uri;
                    try {
                        avatarFile = FileUtil.uriToFile(DetailChange.this,UriImage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    GetQiniuToken(avatarFile);
                }
            }
    );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_change);
        checkbtn = findViewById(R.id.btncheck);
        textCollege =findViewById(R.id.textCollege);
        textNickName = findViewById(R.id.textNickName);
        textID = findViewById(R.id.textID);
        textGender = findViewById(R.id.textsex);
        textMBTI = findViewById(R.id.textMBTI);
        textNote = findViewById(R.id.edtnote);
        avatar = findViewById(R.id.imageviewAvatar);
        SignOutBtn = findViewById(R.id.btnLogOut);
        viewModel = new ViewModelProvider(this).get(CCNU_ViewModel.class);
        viewModel.getData().observe(this, newData -> {
            // 数据发生变化，刷新界面
            updateUI(newData);
        });
        User_token = CCNU_Application.getUser_Token();
        api = CCNU_Application.getApi();
        detailGet();
        yearPicker = findViewById(R.id.yearnumberroll);
        // 设置年份范围，这里设置为 1900 年至当前年份
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        yearPicker.setMinValue(2000);
        yearPicker.setMaxValue(currentYear);

        // 设置默认选中的年份为当前年份
        yearPicker.setValue(year);

        // 设置年份选择监听器
        yearPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                year = newVal;
                updateDayPicker();
            }
        });
        monthPicker = findViewById(R.id.monthnnumberroll);

        // 设置年份范围，这里设置为 1900 年至当前年份
        int currentmonth = calendar.get(Calendar.MONTH);
        monthPicker.setMinValue(01);
        monthPicker.setMaxValue(12);

        // 设置默认选中的年份为当前年份
        monthPicker.setValue(month);
        dayPicker.setValue(day);

        // 设置年份选择监听器
        monthPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                month = newVal;
                updateDayPicker();
            }
        });
        dayPicker = findViewById(R.id.daynumberroll);

        // 设置年份范围，这里设置为 1900 年至当前年份
        updateDayPicker();

        // 设置年份选择监听器
        dayPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                day = newVal;
            }
        });
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        SignOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CCNU_Application.TokensignOut();
                Intent intent = StartActivity.newIntent(DetailChange.this);
                startActivity(intent);
                finishAffinity();
            }
        });
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nickname = textNickName.getText().toString();
                String Mbti = textMBTI.getText().toString();
                String note = textNote.getText().toString();
                String date = ""+year+"-";
                if(month<10) date+="0";
                date+=month+"-";
                if(day<10) date+="0";
                date+=day;
                UploadNew(User_token,nickname,"18",date,note,Mbti);
                Intent intent = PersonalPage.newIntent(DetailChange.this,CCNU_Application.getUserID());
                startActivity(intent);
                finish();
            }
        });
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContentLauncher.launch("image/*");
            }
        });

    }
    public void onBackPressed(){
        Intent intent = PersonalPage.newIntent(DetailChange.this,CCNU_Application.getUserID());
        startActivity(intent);
        finish();
    }
    private void updateDayPicker() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month - 1);
        int maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        dayPicker.setMinValue(1);
        dayPicker.setMaxValue(maxDay);
    }
    private void detailGet(){
        Call<JsonRespond<PersonalDetailData>> DetailGet = api.getPersonalDetail("Bearer "+User_token,CCNU_Application.getUserID());
        DetailGet.enqueue(new Callback<JsonRespond<PersonalDetailData>>() {
            @Override
            public void onResponse(Call<JsonRespond<PersonalDetailData>> call, Response<JsonRespond<PersonalDetailData>> response) {
                //Toast.makeText(DetailChange.this, "请求成功", Toast.LENGTH_SHORT).show();
                JsonRespond<PersonalDetailData> body = response.body();
                if (body == null) {
                    //Toast.makeText(DetailChange.this, "响应体为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Data = body.getData();
                viewModel.updateData(Data);
            }
            @Override
            public void onFailure(Call<JsonRespond<PersonalDetailData>> call, Throwable t) {
                //Toast.makeText(DetailChange.this,"请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void UploadNew(String token,String nickname,String age,String date,String note,String mbti ){
        Call<JsonRespond<SimpleData>> postDetail = api.postDetail("Bearer "+token,nickname,age,date,note,mbti);
        postDetail.enqueue(new Callback<JsonRespond<SimpleData>>() {
            @Override
            public void onResponse(Call<JsonRespond<SimpleData>> call, Response<JsonRespond<SimpleData>> response) {
                Toast.makeText(DetailChange.this,"修改成功",Toast.LENGTH_SHORT).show();
                JsonRespond<SimpleData> body = response.body();
                if(body == null) return;
                detailGet();
            }

            @Override
            public void onFailure(Call<JsonRespond<SimpleData>> call, Throwable t) {

            }
        });
    }
    private void updateUI(PersonalDetailData newData)
    {
//        textStayDate.setText(newData.getStayDate().toString());
        textCollege.setText(newData.getCollege());
        textNickName.setText(newData.getNickname());
        textID.setText(newData.getStuid());
        textGender.setText(newData.getGender());
        String imageurl="https://pic.imgdb.cn/item/65e9ca429f345e8d03be51dc.jpg";
        if(!newData.getHeadimage().equals("")) imageurl=newData.getHeadimage();
        Glide.with(this)
                .load(imageurl)
                .circleCrop()
                .into(avatar);
        textMBTI.setText(newData.getMbti());
        textNote.setText(newData.getSign());
    }
    public void UploadToQiniu(File avatarFile,String QiniuToken)
    {
        UploadManager uploadManager = CCNU_Application.getUploadManager();
        uploadManager.put(avatarFile, null, QiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                //Toast.makeText(DetailChange.this,"Qiniu请求完成",Toast.LENGTH_SHORT).show();
                if(info.isOK()) {
                    //Toast.makeText(DetailChange.this,"Qiniu请求成功",Toast.LENGTH_SHORT).show();
                    String uploadedKey = response.optString("key");
                    String imageKey=uploadedKey;
                    Data.setHeadimage("http://mini-project.muxixyz.com/"+imageKey);
                    updateUI(Data);
                    UpLoadKey(imageKey);
                } else {
                    Log.i("qiniu", "Upload Fail");
                    //Toast.makeText(DetailChange.this,info.toString(),Toast.LENGTH_SHORT).show();
                    //如果失败，这里可以把 info 信息上报自己的服务器，便于后面分析上传错误原因
                }
                //Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
            }
        },null);
    }
    public void UpLoadKey(String uploadedKey)
    {
        Call<JsonRespond<SimpleData>> avatarUploadResponseCall = api.avatarKeyUpload("Bearer "+User_token,uploadedKey);
        avatarUploadResponseCall.enqueue(new Callback<JsonRespond<SimpleData>>() {
            @Override
            public void onResponse(Call<JsonRespond<SimpleData>> call, Response<JsonRespond<SimpleData>> response) {
                //Toast.makeText(DetailChange.this,"上传Key成功",Toast.LENGTH_SHORT).show();
                JsonRespond<SimpleData> body = response.body();
                if(body == null) return;
            }
            @Override
            public void onFailure(Call<JsonRespond<SimpleData>> call, Throwable t) {
               // Log.i("KeyUpload","Failed");
            }
        });
    }
    public void GetQiniuToken(File avatarFile)
    {
        Call<QnTokenJson> QiniuTokenGet = api.getQiniuToken("Bearer "+User_token);
        QiniuTokenGet.enqueue(new Callback<QnTokenJson>() {
            @Override
            public void onResponse(Call<QnTokenJson> call, Response<QnTokenJson> response) {
                //Toast.makeText(DetailChange.this,"Token请求成功",Toast.LENGTH_SHORT).show();
                QnTokenJson body = response.body();
                if(body==null) return;
                String QiniuToken = body.getQnToken();
                Toast.makeText(DetailChange.this,QiniuToken,Toast.LENGTH_SHORT).show();
                UploadToQiniu(avatarFile,QiniuToken);
            }
            @Override
            public void onFailure(Call<QnTokenJson> call, Throwable t) {
                //Toast.makeText(DetailChange.this,"Token请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //提交新建的record到服务器并返回成功与否
}