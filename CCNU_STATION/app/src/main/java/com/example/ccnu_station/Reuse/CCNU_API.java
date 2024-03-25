package com.example.ccnu_station.Reuse;

import com.example.ccnu_station.Achivement.AchievementClickResponse;
import com.example.ccnu_station.Achivement.AchievementTotalFinishedResponse;
import com.example.ccnu_station.Finder.FinderResponseData;
import com.example.ccnu_station.Login.LoginData;
import com.example.ccnu_station.Personal.PersonalDetailData;
import com.example.ccnu_station.Record.RecordResponseData;
import okhttp3.RequestBody;
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
    @FormUrlEncoded
    @POST("api/post/post_treasure_hunting")
    Call<JsonRespond<SimpleData>> postFinder(
            @Header("Authorization")String Token,
            @Query("where")String BuildID,
            @Query("image")String imagekey,
            @Field("clue")String clue,
            @Field("title")String title,
            @Field("deadline") String ddl,
            @Field("thing") String thing
    );
    @FormUrlEncoded
    @POST("api/login")
    Call<JsonRespond<LoginData>> getLoginData(@Field("stuid") String UserID, @Field("password") String Password);
    @FormUrlEncoded
    @POST("api/post/postnote")
    Call<JsonRespond<SimpleData>> postRecord(
            @Header("Authorization")String Token,
            @Query("where")String BuildID,
            @Query("key1")String imagekey,
            @Field("text")String content,
            @Field("title")String title
    );
    @GET("api/getactivity/alltreasurehunting")
    Call<JsonRespond<FinderResponseData>> getAllFindings(@Query("where") String ID);
    @GET("api/getactivity/allpostnote")
    Call<JsonRespond<RecordResponseData>> getAllRecords(@Query("where") String ID);
    @GET("api/user/detail")
    Call<JsonRespond<PersonalDetailData>> getPersonalDetail(@Header("Authorization") String token, @Query("userid") String ID);
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
    Call<AchievementClickResponse> getAchievementClickResponse(@Query("stuid") String UserID, @Query("achid") String AchID);

}
