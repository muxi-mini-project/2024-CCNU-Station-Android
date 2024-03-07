package com.example.ccnu_station;

import android.content.Context;
import android.widget.Toast;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ImageUrl {
    private String returnedUrl = "null";
    public void ImageSubmit(Context ActivityName,String token,File file)
    {
        CCNU_API api = CCNU_Application.getApi();
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"),file);
        Call<ImageUrlResponse> call = api.uploadImage("Bearer "+token,requestBody);
        call.enqueue(new Callback<ImageUrlResponse>() {
            @Override
            public void onResponse(Call<ImageUrlResponse> call, Response<ImageUrlResponse> response) {
                Toast.makeText(ActivityName,"上传成功",Toast.LENGTH_SHORT).show();
                ImageUrlResponse body = response.body();
                if(body == null) return;
                returnedUrl = body.getUrl();
            }
            @Override
            public void onFailure(Call<ImageUrlResponse> call, Throwable t) {
                Toast.makeText(ActivityName,"上传失败",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public String getReturnedUrl() {
        return returnedUrl;
    }
}
