package com.example.ccnu_station;

import android.icu.text.IDNA;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface CCNU_API {
    @GET("api/user/detail")
    Call<PersonalDetailData> getPersonalDetail(@Header("Authorization") String token);
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginData> getLoginData(@Field("stuid") String UserID, @Field("password") String Password);
}
