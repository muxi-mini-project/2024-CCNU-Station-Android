package com.example.ccnu_station.Reuse;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.ccnu_station.Reuse.CCNU_API;
import com.qiniu.android.common.AutoZone;
import com.qiniu.android.common.Zone;
import com.qiniu.android.storage.Configuration;
import com.qiniu.android.storage.UploadManager;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CCNU_Application extends Application {
    private static String User_Token;
    private static CCNU_API api;
    private static UploadManager uploadManager;
    public void onCreate()
    {
        super.onCreate();
        //获取本地存储的UserToken
        SharedPreferences sp = getSharedPreferences("User_Details", Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = sp.edit();
        edt.putString("token","null");
        edt.commit();
        User_Token = sp.getString("token","null");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://47.92.102.209:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(CCNU_API.class);

        // AutoZone：自动根据 bucket 去查询相应 Zone，Zone 信息会被缓存
        Zone zone = new AutoZone();
        // 根据区域 ID 创建 Zone，无需查询，强烈推荐使用 AutoZone
        //zone = FixedZone.createWithRegionId("z0");
        Configuration config = new Configuration.Builder()
                .connectTimeout(90)              // 链接超时。默认90秒
                .useHttps(true)                  // 是否使用https上传域名
                .useConcurrentResumeUpload(true) // 使用并发上传，使用并发上传时，除最后一块大小不定外，其余每个块大小固定为4M，
                .resumeUploadVersion(Configuration.RESUME_UPLOAD_VERSION_V2) // 使用新版分片上传
                .concurrentTaskCount(3)          // 并发上传线程数量为3
                .responseTimeout(90)             // 服务器响应超时。默认90秒
                .recorder(null)              // recorder分片上传时，已上传片记录器。默认null
                .recorder(null,null)      // keyGen 分片上传时，生成标识符，用于片记录器区分是那个文件的上传记录
                .zone(zone)                      // 设置区域，不指定会默认使用 AutoZone；指定不同区域的上传域名、备用域名、备用IP。
                .build();
        // 重用uploadManager。一般地，只需要创建一个uploadManager对象
        uploadManager = new UploadManager(config);
    }
    public static CCNU_API getApi()
    {
        return api;
    }

    public static UploadManager getUploadManager() {
        return uploadManager;
    }
    public static String getUser_Token(){
        return "null";
    }

}
