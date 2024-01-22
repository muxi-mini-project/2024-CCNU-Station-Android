package com.example.ccnu_station;

import android.icu.text.IDNA;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CCNU_API {
    @FormUrlEncoded
    @POST("api/login")
    Call<Data<LoginData>> getLoginData(@Field("stuid") String UserID, @Field("password") String Password);
}
