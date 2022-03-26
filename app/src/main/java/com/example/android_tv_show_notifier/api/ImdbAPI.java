package com.example.android_tv_show_notifier.api;

import com.example.android_tv_show_notifier.models.ActorModel;
import com.example.android_tv_show_notifier.models.MostPopularDataModel;
import com.example.android_tv_show_notifier.models.NameModel;
import com.example.android_tv_show_notifier.models.NewMovieDataModel;
import com.example.android_tv_show_notifier.models.TitleModel;
import com.example.android_tv_show_notifier.models.TrailerModel;

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

    @GET("InTheaters/k_9od86016")
    Call<NewMovieDataModel> getInTheaters();

    @GET("Title/k_9od86016/" + "{id}")
    Call<TitleModel> getTitle(@Path("id") String id);

    @GET("Trailer/k_9od86016/" + "{id}")
    Call<TrailerModel> getTrailer(@Path("id") String id);

    @GET("Name/k_9od86016/" + "{id}")
    Call<NameModel> getActor(@Path("id") String id);

}
