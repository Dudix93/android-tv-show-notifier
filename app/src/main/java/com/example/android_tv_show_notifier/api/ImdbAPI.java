package com.example.android_tv_show_notifier.api;

import com.example.android_tv_show_notifier.models.MostPopularDataModel;
import com.example.android_tv_show_notifier.models.NewMovieDataModel;
import com.example.android_tv_show_notifier.models.TitleModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ImdbAPI {

    @GET("MostPopularMovies/k_9od86016")
    Call<MostPopularDataModel> getMostPopularMovies();

    @GET("MostPopularTVs/k_9od86016")
    Call<MostPopularDataModel> getMostPopularTVs();

    @GET("ComingSoon/k_9od86016")
    Call<NewMovieDataModel> getComingSoon();

    @GET("Title/k_9od86016/" + "{id}")
    Call<TitleModel> getTitle(@Path("id") String id);

}
