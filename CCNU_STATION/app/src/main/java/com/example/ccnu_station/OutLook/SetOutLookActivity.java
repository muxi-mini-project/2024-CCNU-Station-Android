package com.example.ccnu_station.OutLook;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.CCNU_ViewModel;
import com.example.ccnu_station.Reuse.FileUtil;
import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Reuse.JsonRespond;
import com.example.ccnu_station.Reuse.QnTokenJson;
import com.example.ccnu_station.Reuse.SimpleData;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetOutLookActivity extends BaseActivity {
    private CCNU_API api;
    private Uri UriAvatar;
    private String QiniuToken;
    private SetOutLookActivityData Data;
    private CCNU_ViewModel<SetOutLookActivityData> viewModel;
    /*
    private static final String User_Identity =
            "com.example.ccnu_station.OutLook.SetOutLookActivity.UserIdentity";

     */
    private String User_token=CCNU_Application.getUser_Token();
    //private TextView textTest;
    private ImageButton btnNext;
    private ImageView avatar;
    public static Intent newIntent(Context packgeContext)
    {
        Intent intent = new Intent(packgeContext, SetOutLookActivity.class);
        return intent;
    }
    private ActivityResultLauncher<String> getContentLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    File avatarFile;
                    UriAvatar = uri;
                    try {
                        avatarFile = FileUtil.uriToFile(SetOutLookActivity.this,UriAvatar);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    GetQiniuToken(avatarFile);
                }
            }
    );
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_out_look);
        RequestOptions requestOptions = new RequestOptions()
                .placeholder(android.R.color.white) // 使用系统的白色作为占位符
                .error(android.R.color.white); // 在加载出错时同样使用白色
        api = CCNU_Application.getApi();
        Data = new SetOutLookActivityData();
        btnNext = findViewById(R.id.btnNext);
        avatar = findViewById(R.id.imageViewAvatar);
        updateUI(Data);
        viewModel = new ViewModelProvider(this).get(CCNU_ViewModel.class);
        viewModel.getData().observe(this, newData -> {
            // 数据发生变化，刷新界面
            updateUI(newData);
        });
        //textTest = (TextView) findViewById(R.id.textTest);
        //textTest.setText(User);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = HomePage.newIntent(SetOutLookActivity.this);
                startActivity(intent);
            }
        });
        avatar.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                getContentLauncher.launch("image/*");
            }
        });
    }
    private void updateUI(SetOutLookActivityData newData)
    {
        Glide.with(this)
                .load(newData.getAvatarUrl())
                .circleCrop()
                .into(avatar);
    }
    public void UpLoadKey(String uploadedKey)
    {
        Call<JsonRespond<SimpleData>> avatarUploadResponseCall = api.avatarKeyUpload("Bearer "+User_token,uploadedKey);
        avatarUploadResponseCall.enqueue(new Callback<JsonRespond<SimpleData>>() {
            @Override
            public void onResponse(Call<JsonRespond<SimpleData>> call, Response<JsonRespond<SimpleData>> response) {
                Toast.makeText(SetOutLookActivity.this,"上传Key成功",Toast.LENGTH_SHORT).show();
                JsonRespond<SimpleData> body = response.body();
                if(body == null) return;
                String imageUrl = "http://mini-project.muxixyz.com/" + uploadedKey;
                String avatarUrl = imageUrl;
                Data.setAvatarUrl(avatarUrl);
                viewModel.updateData(Data);
            }
            @Override
            public void onFailure(Call<JsonRespond<SimpleData>> call, Throwable t) {
                Log.i("KeyUpload","Failed");
            }
        });
    }
    public void UploadToQiniu(File avatarFile)
    {
        UploadManager uploadManager = CCNU_Application.getUploadManager();
        uploadManager.put(avatarFile, null, QiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                Toast.makeText(SetOutLookActivity.this,"Qiniu请求完成",Toast.LENGTH_SHORT).show();
                if(info.isOK()) {
                    Toast.makeText(SetOutLookActivity.this,"Qiniu请求成功",Toast.LENGTH_SHORT).show();
                    String uploadedKey = response.optString("key");
                    UpLoadKey(uploadedKey);
                                        /*
                                        Glide.with(SetOutLookActivity.this)
                                                .load(JsonRespond.getAvatarUrl())
                                                .circleCrop()
                                                .into(avatar);
                                         */
                    //Log.i("qiniu", "Uploaded Image URL: " + imageUrl);
                } else {
                    Log.i("qiniu", "Upload Fail");
                    Toast.makeText(SetOutLookActivity.this,info.toString(),Toast.LENGTH_SHORT).show();
                    //如果失败，这里可以把 info 信息上报自己的服务器，便于后面分析上传错误原因
                }
                //Log.i("qiniu", key + ",\r\n " + info + ",\r\n " + res);
            }
        },null);
    }
    public void GetQiniuToken(File avatarFile)
    {
        Call<QnTokenJson> QiniuTokenGet = api.getQiniuToken("Bearer "+User_token);
        QiniuTokenGet.enqueue(new Callback<QnTokenJson>() {
            @Override
            public void onResponse(Call<QnTokenJson> call, Response<QnTokenJson> response) {
                Toast.makeText(SetOutLookActivity.this,"Token请求成功",Toast.LENGTH_SHORT).show();
                QnTokenJson body = response.body();
                if(body==null) return;
                QiniuToken = body.getQnToken();
                Toast.makeText(SetOutLookActivity.this,QiniuToken,Toast.LENGTH_SHORT).show();
                UploadToQiniu(avatarFile);
            }

            @Override
            public void onFailure(Call<QnTokenJson> call, Throwable t) {
                Toast.makeText(SetOutLookActivity.this,"Token请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
