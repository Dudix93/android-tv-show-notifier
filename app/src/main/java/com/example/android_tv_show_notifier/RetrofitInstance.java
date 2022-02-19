package com.example.android_tv_show_notifier;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {

     public TodoAPI api = new Retrofit.Builder()
             .baseUrl("https://jsonplaceholder.typicode.com")
             .addConverterFactory(GsonConverterFactory.create())
             .build()
             .create(TodoAPI.class);

}
