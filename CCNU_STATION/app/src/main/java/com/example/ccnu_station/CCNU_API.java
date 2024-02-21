package com.example.ccnu_station;

import com.example.ccnu_station.Login.LoginData;
import com.example.ccnu_station.OutLook.QiniuTokenData;
import com.example.ccnu_station.Personal.PersonalDetailData;
import com.example.ccnu_station.Record.addRecordBody;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
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
    Call<PersonalDetailData> getPersonalDetail(@Header("Authorization") String token, @Query("userid") String ID);
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginData> getLoginData(@Field("stuid") String UserID, @Field("password") String Password);
    @Multipart
    @POST("api/image")
    Call<ImageUrlResponse> uploadImage(
            @Header("Authorization") String authorization,
            @Part("image") RequestBody image);
    @GET("api/user/avatar")
    Call<AvatarUploadResponse> avatarKeyUpload(@Header("Authorization")String Token,@Query("image") String key);
    @POST("api/post/postnote")
    Call<Data> addRecordsPost(
            @Header("Authorization") String token,
            @Query("key1") String key1,
            @Query("key2") String key2,
            @Query("key3") String key3,
            @Query("key4") String key4,
            @Query("key5") String key5,
            @Body addRecordBody body);
    @GET("qiniutoken")
    Call<QiniuTokenData> getQiniuToken(@Header("Authorization")String Token);

}
