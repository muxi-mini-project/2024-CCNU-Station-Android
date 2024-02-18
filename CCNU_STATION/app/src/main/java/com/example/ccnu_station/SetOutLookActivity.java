package com.example.ccnu_station;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.style.UpdateLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.QuickContactBadge;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetOutLookActivity extends AppCompatActivity {
    private CCNU_API api;
    private Uri UriAvatar;
    private String QiniuToken;
    private SetOutLookActivityData Data;
    private CCNU_ViewModel<SetOutLookActivityData> viewModel;
    /*
    private static final String User_Identity =
            "com.example.ccnu_station.SetOutLookActivity.UserIdentity";

     */
    private String User_token="null";
    //private TextView textTest;
    private Button btnNext;
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
        api = CCNU_Application.getApi();
        Data = new SetOutLookActivityData();
        btnNext = findViewById(R.id.btnNext);
        avatar = findViewById(R.id.imageViewAvatar);
        SharedPreferences sp = getSharedPreferences("User_Details",Context.MODE_PRIVATE);
        updateUI(Data);
        viewModel = new ViewModelProvider(this).get(CCNU_ViewModel.class);
        viewModel.getData().observe(this, newData -> {
            // 数据发生变化，刷新界面
            updateUI(newData);
        });

        User_token = sp.getString("token","null");
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
        Call<AvatarUploadResponse> avatarUploadResponseCall = api.avatarKeyUpload("Bearer "+User_token,uploadedKey);
        avatarUploadResponseCall.enqueue(new Callback<AvatarUploadResponse>() {
            @Override
            public void onResponse(Call<AvatarUploadResponse> call, Response<AvatarUploadResponse> response) {
                Toast.makeText(SetOutLookActivity.this,"上传Key成功",Toast.LENGTH_SHORT).show();
                AvatarUploadResponse body = response.body();
                if(body == null) return;
                if(!body.getSuccess()) return;
                String imageUrl = "http://mini-project.muxixyz.com/" + uploadedKey;
                String avatarUrl = imageUrl;
                Data.setAvatarUrl(avatarUrl);
                viewModel.updateData(Data);
            }

            @Override
            public void onFailure(Call<AvatarUploadResponse> call, Throwable t) {
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
                                                .load(Data.getAvatarUrl())
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
        Call<QiniuTokenData> QiniuTokenGet = api.getQiniuToken("Bearer "+User_token);
        QiniuTokenGet.enqueue(new Callback<QiniuTokenData>() {
            @Override
            public void onResponse(Call<QiniuTokenData> call, Response<QiniuTokenData> response) {
                Toast.makeText(SetOutLookActivity.this,"Token请求成功",Toast.LENGTH_SHORT).show();
                QiniuTokenData body = response.body();
                if(body==null) return;
                QiniuToken = body.getQnToken();
                Toast.makeText(SetOutLookActivity.this,QiniuToken,Toast.LENGTH_SHORT).show();
                UploadToQiniu(avatarFile);
            }

            @Override
            public void onFailure(Call<QiniuTokenData> call, Throwable t) {
                Toast.makeText(SetOutLookActivity.this,"Token请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}
