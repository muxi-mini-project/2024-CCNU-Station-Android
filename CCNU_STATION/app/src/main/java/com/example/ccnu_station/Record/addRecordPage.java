package com.example.ccnu_station.Record;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ccnu_station.CCNU_API;
import com.example.ccnu_station.CCNU_Application;
import com.example.ccnu_station.FileUtil;
import com.example.ccnu_station.OutLook.QiniuTokenData;
import com.example.ccnu_station.OutLook.SetOutLookActivity;
import com.example.ccnu_station.R;
import com.qiniu.android.http.ResponseInfo;
import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UploadManager;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addRecordPage extends AppCompatActivity {

    private CCNU_API api=CCNU_Application.getApi();
    private String User_token="null";
    private RecordData keydata= new RecordData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_page);
    }
    public static Intent newIntent(Context packgeContext)
    {
        Intent intent = new Intent(packgeContext, addRecordPage.class);
        return intent;
    }
    private ActivityResultLauncher<String> getContentLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    File avatarFile;
                    Uri UriImage = uri;
                    try {
                        avatarFile = FileUtil.uriToFile(addRecordPage.this,UriImage);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    GetQiniuToken(avatarFile);
                }
            }
    );
    //上传图片并返回key
    public void UploadToQiniu(File avatarFile,String QiniuToken)
    {
        UploadManager uploadManager = CCNU_Application.getUploadManager();
        uploadManager.put(avatarFile, null, QiniuToken, new UpCompletionHandler() {
            @Override
            public void complete(String key, ResponseInfo info, JSONObject response) {
                Toast.makeText(addRecordPage.this,"Qiniu请求完成",Toast.LENGTH_SHORT).show();
                if(info.isOK()) {
                    Toast.makeText(addRecordPage.this,"Qiniu请求成功",Toast.LENGTH_SHORT).show();
                    String uploadedKey = response.optString("key");
                    keydata.setKey(uploadedKey);
                } else {
                    Log.i("qiniu", "Upload Fail");
                    Toast.makeText(addRecordPage.this,info.toString(),Toast.LENGTH_SHORT).show();
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
                Toast.makeText(addRecordPage.this,"Token请求成功",Toast.LENGTH_SHORT).show();
                QiniuTokenData body = response.body();
                if(body==null) return;
                String QiniuToken = body.getQnToken();
                Toast.makeText(addRecordPage.this,QiniuToken,Toast.LENGTH_SHORT).show();
                UploadToQiniu(avatarFile,QiniuToken);
            }
            @Override
            public void onFailure(Call<QiniuTokenData> call, Throwable t) {
                Toast.makeText(addRecordPage.this,"Token请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //提交新建的record到服务器并返回成功与否
}