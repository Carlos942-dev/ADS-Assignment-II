package com.ninja.fruitclient;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit getRerofit() {

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(loggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://192.168.200.9:8080/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }

    public  static FruitService getVegService(){
        FruitService fruitService = getRerofit().create(FruitService.class);
        return fruitService;
    }


}
