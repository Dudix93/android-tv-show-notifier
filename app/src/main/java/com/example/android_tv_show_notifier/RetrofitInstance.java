package com.example.android_tv_show_notifier;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

     public ImdbAPI api = new Retrofit.Builder()
             .baseUrl("https://imdb-api.com/en/API/")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(ImdbAPI.class);

}
