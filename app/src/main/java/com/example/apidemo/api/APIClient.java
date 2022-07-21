package com.example.apidemo.api;

import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    //https://www.journaldev.com/13639/retrofit-android-example-tutorial


//    http://157.245.111.180/public/api/all_genres

    private static Retrofit retrofit = null;
    public static String url = "http://157.245.111.180/public/api/";

    public static Retrofit getClient(Context context) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(2000, TimeUnit.SECONDS);
        httpClient.readTimeout(2000, TimeUnit.SECONDS);
        httpClient.writeTimeout(2000, TimeUnit.SECONDS);
        httpClient.addInterceptor(interceptor);
        httpClient.addInterceptor(new NetworkConnectionInterceptor(context));

        retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit;
    }
}
