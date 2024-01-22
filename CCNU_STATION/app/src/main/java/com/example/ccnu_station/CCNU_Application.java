package com.example.ccnu_station;

import android.app.Application;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CCNU_Application extends Application {
    private static CCNU_API api;
    public void onCreat()
    {
        super.onCreate();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(CCNU_API.class);
    }
    public static CCNU_API getApi()
    {
        return api;
    }
}
