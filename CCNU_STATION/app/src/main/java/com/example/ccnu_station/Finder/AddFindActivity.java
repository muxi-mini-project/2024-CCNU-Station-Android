package com.example.ccnu_station.Finder;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

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
import com.example.ccnu_station.Call.AddCallActivity;
import com.example.ccnu_station.Call.CallActivity;
import com.example.ccnu_station.Home.HomePage;
import com.example.ccnu_station.Login.LoginActivity;
import com.example.ccnu_station.R;
import com.example.ccnu_station.Record.RecordActivity;
import com.example.ccnu_station.Record.addRecordActivity;
import com.example.ccnu_station.Reuse.BaseActivity;
import com.example.ccnu_station.Reuse.CCNU_API;
import com.example.ccnu_station.Reuse.CCNU_Application;
import com.example.ccnu_station.Reuse.FileUtil;
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

public class AddFindActivity extends BaseActivity {
    private String UserID = CCNU_Application.getUserID();
    private CCNU_API api=CCNU_Application.getApi();
    private String User_token=CCNU_Application.getUser_Token();
    private EditText title;
    private EditText thing;
    private EditText clue;
    private EditText ddl;
    private String imageKey;
    private String BuildID;
    private ImageView background;
    private ImageButton image;
    private Button checkbtn;
    private Button cancelbtn;
    private static final String BUILDID =
            "com.example.ccnu_station.AddFindActivity.Building_ID";
    public static Intent newIntent(Context packgeContext, String buildID)
    {
        Intent intent = new Intent(packgeContext, AddFindActivity.class);
        intent.putExtra(BUILDID,buildID);
        return intent;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_find);
        BuildID = getIntent().getStringExtra(BUILDID);
        background = findViewById(R.id.background);
        background.setImageResource(CCNU_Application.buildBackGround[BuildID.toCharArray()[0]-'0'-1]);
        title = findViewById(R.id.edittextFindTitle);
        clue = findViewById(R.id.edtClue);
        thing = findViewById(R.id.edittextFindthing);
        ddl = findViewById(R.id.edtDDL);
        image = findViewById(R.id.imgbtnImage);
        checkbtn = findViewById(R.id.buttonCheck);
        cancelbtn = findViewById(R.id.buttonCancel);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContentLauncher.launch("image/*");
            }
        });
        checkbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Title = title.getText().toString();
                String Clue = clue.getText().toString();
                String DDL = ddl.getText().toString();
                String Thing = thing.getText().toString();
                UploadNewFind(Title,Clue,Thing,DDL,imageKey,User_token,BuildID);
            }
        });
    }
    public void onBackPressed(){
        Intent intent = FinderActivity.newIntent(AddFindActivity.this,BuildID.toCharArray()[0]-'0');
        startActivity(intent);
        finish();
    }
    private ActivityResultLauncher<String> getContentLauncher = registerForActivityResult(
            new ActivityResultContracts.GetContent(),
            uri -> {
                if (uri != null) {
                    File avatarFile;
                    Uri UriImage = uri;
                    try {
                        avatarFile = FileUtil.uriToFile(AddFindActivity.this,UriImage);
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
                Toast.makeText(AddFindActivity.this,"Qiniu请求完成",Toast.LENGTH_SHORT).show();
                if(info.isOK()) {
                    Toast.makeText(AddFindActivity.this,"Qiniu请求成功",Toast.LENGTH_SHORT).show();
                    String uploadedKey = response.optString("key");
                    imageKey=uploadedKey;
                    Glide.with(AddFindActivity.this)
                            .load("http://mini-project.muxixyz.com/"+imageKey)
                            .into(image);
                } else {
                    Log.i("qiniu", "Upload Fail");
                    Toast.makeText(AddFindActivity.this,info.toString(),Toast.LENGTH_SHORT).show();
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
                Toast.makeText(AddFindActivity.this,"Token请求成功",Toast.LENGTH_SHORT).show();
                QnTokenJson body = response.body();
                if(body==null) return;
                String QiniuToken = body.getQnToken();
                Toast.makeText(AddFindActivity.this,QiniuToken,Toast.LENGTH_SHORT).show();
                UploadToQiniu(avatarFile,QiniuToken);
            }
            @Override
            public void onFailure(Call<QnTokenJson> call, Throwable t) {
                Toast.makeText(AddFindActivity.this,"Token请求失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    //提交新建的record到服务器并返回成功与否
    public void UploadNewFind(String title,String clue,String thing,String ddl,String key,String token,String buildID){
        Call<JsonRespond<SimpleData>> recordPoster = api.postFinder(
                "Bearer "+token,
                buildID,
                key,
                clue,
                title,
                ddl,
                thing
        );
        recordPoster.enqueue(new Callback<JsonRespond<SimpleData>>() {
            @Override
            public void onResponse(Call<JsonRespond<SimpleData>> call, Response<JsonRespond<SimpleData>> response) {
                Toast.makeText(AddFindActivity.this,"请求成功",Toast.LENGTH_SHORT).show();
                JsonRespond<SimpleData> body = response.body();
                if(body.getCode()==1000){
                    Toast.makeText(AddFindActivity.this,"发布成功",Toast.LENGTH_SHORT).show();
                }
                if(body.getCode()==2005){
                    Intent intent = LoginActivity.newIntent(AddFindActivity.this);
                    startActivity(intent);
                    finishAffinity();
                }
                else{
                    Toast.makeText(AddFindActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonRespond<SimpleData>> call, Throwable t) {
                Toast.makeText(AddFindActivity.this,"发布失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
}