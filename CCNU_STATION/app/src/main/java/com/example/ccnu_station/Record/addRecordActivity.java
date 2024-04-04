package com.example.ccnu_station.Record;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ccnu_station.Call.CallActivity;
import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.FileUtil;
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
import java.net.URI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class addRecordActivity extends BaseActivity {

    private CCNU_API api=CCNU_Application.getApi();
    private String User_token=CCNU_Application.getUser_Token();
    private static final String BUILDID =
            "com.example.ccnu_station.addRecordActivity.Building_ID";
    private String imageKey;
    private ImageView background;
    private String UserID = CCNU_Application.getUserID();
    private Button btnCheck;
    private Button btnCancel;
    private EditText editContent;
    private String BuildID;
    private EditText editTitle;
    private ImageButton btnimage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record_page);
        BuildID = getIntent().getStringExtra(BUILDID);
        background = findViewById(R.id.background);
        background.setImageResource(CCNU_Application.buildBackGround[BuildID.toCharArray()[0]-'0'-1]);
        btnCheck = findViewById(R.id.buttonCheck);
        btnCancel = findViewById(R.id.buttonCancel);
        btnimage = findViewById(R.id.imgbtnImage);
        editContent =findViewById(R.id.edittextContent);
        editTitle = findViewById(R.id.edittextTitle);
        btnimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContentLauncher.launch("image/*");
            }
        });
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rdtitle = editTitle.getText().toString();
                String rdcontent = editContent.getText().toString();
                UploadNewRecord(rdtitle,rdcontent,imageKey,User_token,BuildID);
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = RecordActivity.newIntent(addRecordActivity.this,BuildID.toCharArray()[0]-'0');
                startActivity(intent);
                finish();
            }
        });
    }
    public void onBackPressed(){
        Intent intent = RecordActivity.newIntent(addRecordActivity.this,BuildID.toCharArray()[0]-'0');
        startActivity(intent);
        finish();
    }
    public static Intent newIntent(Context packgeContext,String buildID)
    {
        Intent intent = new Intent(packgeContext, addRecordActivity.class);
        intent.putExtra(BUILDID,buildID);
        return intent;
    }
    private ActivityResultLauncher<String> getContentLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    File avatarFile;
                    Uri UriImage = uri;
                    try {
                        avatarFile = FileUtil.uriToFile(addRecordActivity.this,UriImage);
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
                //Toast.makeText(addRecordActivity.this,"Qiniu请求完成",Toast.LENGTH_SHORT).show();
                if(info.isOK()) {
                   // Toast.makeText(addRecordActivity.this,"Qiniu请求成功",Toast.LENGTH_SHORT).show();
                    String uploadedKey = response.optString("key");
                    imageKey=uploadedKey;
                    Glide.with(addRecordActivity.this)
                            .load("http://mini-project.muxixyz.com/"+imageKey)
                            .into(btnimage);
                } else {
                    Log.i("qiniu", "Upload Fail");
                   // Toast.makeText(addRecordActivity.this,info.toString(),Toast.LENGTH_SHORT).show();
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
              //  Toast.makeText(addRecordActivity.this,"Token请求成功",Toast.LENGTH_SHORT).show();
                QnTokenJson body = response.body();
                if(body==null) return;
                String QiniuToken = body.getQnToken();
                //Toast.makeText(addRecordActivity.this,QiniuToken,Toast.LENGTH_SHORT).show();
                UploadToQiniu(avatarFile,QiniuToken);
            }
            @Override
            public void onFailure(Call<QnTokenJson> call, Throwable t) {
                //Toast.makeText(addRecordActivity.this,"Token请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //提交新建的record到服务器并返回成功与否
    public void UploadNewRecord(String title,String content,String key,String token,String BuildID){
        Call<JsonRespond<SimpleData>> recordPoster = api.postRecord(
                "Bearer "+token,
                BuildID,
                key,
                content,
                title
                );
        recordPoster.enqueue(new Callback<JsonRespond<SimpleData>>() {
            @Override
            public void onResponse(Call<JsonRespond<SimpleData>> call, Response<JsonRespond<SimpleData>> response) {
               // Toast.makeText(addRecordActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                JsonRespond<SimpleData> body = response.body();
                if(body.getCode()==1000){
                 //   Toast.makeText(addRecordActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(addRecordActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonRespond<SimpleData>> call, Throwable t) {
                Toast.makeText(addRecordActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}