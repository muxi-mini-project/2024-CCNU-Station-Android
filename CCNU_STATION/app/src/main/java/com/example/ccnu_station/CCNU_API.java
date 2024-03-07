package com.example.ccnu_station;

import android.icu.text.IDNA;

import java.util.SplittableRandom;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface CCNU_API {
    @GET("api/user/detail")
    Call<PersonalDetailData> getPersonalDetail(@Header("Authorization") String token);
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginData> getLoginData(@Field("stuid") String UserID, @Field("password") String Password);
    @Multipart
    @POST("api/image")
    Call<ImageUrlResponse> uploadImage(
            @Header("Authorization") String authorization,
            @Part("image") RequestBody image);
    @GET("qiniutoken")
    Call<QiniuTokenData> getQiniuToken(@Header("Authorization")String Token);
    @GET("api/user/avatar")
    Call<AvatarUploadResponse> avatarKeyUpload(@Header("Authorization")String Token,@Query("image") String key);
}
