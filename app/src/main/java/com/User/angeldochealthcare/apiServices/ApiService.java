package com.User.angeldochealthcare.apiServices;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private final String key = "manager";
    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(500, TimeUnit.SECONDS)
                .readTimeout(100, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.29.231:1947/api/v1/")
                //     .baseUrl("http://192.168.29.56:3000/api/v1/")
                .client(okHttpClient)
                .build();
        return retrofit;
    }
    public static ApiHolder apiHolders() {
        ApiHolder apiHolders = getRetrofit().create(ApiHolder.class);
        return apiHolders;
    }
}
