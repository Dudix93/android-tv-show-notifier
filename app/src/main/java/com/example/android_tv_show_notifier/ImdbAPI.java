package com.example.android_tv_show_notifier;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ImdbAPI {

    @GET("MostPopularMovies/k_9od86016")
    Call<MostPopularDataModel> getMostPopularMovies();

}
