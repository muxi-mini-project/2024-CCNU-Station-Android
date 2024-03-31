package com.example.ccnu_station.Reuse;

import com.example.ccnu_station.Achievement.AchievementClickResponse;
import com.example.ccnu_station.Achievement.AchievementTotalFinishedResponse;
import com.example.ccnu_station.Login.LoginData;
import com.example.ccnu_station.Personal.PersonalDetailData;
import com.example.ccnu_station.Record.RecordResponseData;
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
    @FormUrlEncoded
    @POST("api/post/postnote")
    Call<JsonRespond<SimpleData>> postRecord(
            @Header("Authorization")String Token,
            @Query("where")String BuildID,
            @Query("key1")String imagekey,
            @Field("text")String content,
            @Field("title")String title
    );
    @GET("api/getactivity/allpostnote")
    Call<JsonRespond<RecordResponseData>> getAllRecords(@Query("where") String ID);
    @GET("api/user/detail")
    Call<JsonRespond<PersonalDetailData>> getPersonalDetail(@Header("Authorization") String token, @Query("userid") String ID);
    @FormUrlEncoded
    @POST("api/login")
    Call<JsonRespond<LoginData>> getLoginData(@Field("stuid") String UserID, @Field("password") String Password);
    @Multipart
    @POST("api/image")
    Call<JsonRespond> uploadImage(
            @Header("Authorization") String authorization,
            @Part("image") RequestBody image);
    @GET("api/user/avatar")
    Call<JsonRespond<SimpleData>> avatarKeyUpload(@Header("Authorization")String Token, @Query("image") String key);
    @GET("qiniutoken")
    Call<QnTokenJson> getQiniuToken(@Header("Authorization")String Token);

    @GET("api/user/achievement/get")
    Call<AchievementTotalFinishedResponse> getAchievementTotalFinished(@Query("stuid") String UserID);

    @GET("api/user/achievement/update")
    Call<AchievementClickResponse> getAchievementReusult(@Query("stuid") String UserID,@Query("achid") String AchiID);


}
