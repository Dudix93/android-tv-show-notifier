package com.example.android_tv_show_notifier.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.adapters.MoviesListAdapter;
import com.example.android_tv_show_notifier.api.ImdbAPI;
import com.example.android_tv_show_notifier.api.RetrofitInstance;
import com.example.android_tv_show_notifier.models.MostPopularDataDetailModel;
import com.example.android_tv_show_notifier.models.MostPopularDataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MostPopularDataDetailModel> moviesArrayList;
    private RecyclerView moviesRecyclerView;
    private RecyclerView.Adapter moviesListAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = getApplicationContext();
        ImdbAPI imdbAPI = new RetrofitInstance().api;

        Call<MostPopularDataModel> call = imdbAPI.getMostPopularTVs();

        try {
            call.enqueue(new Callback<MostPopularDataModel>() {
                @Override
                public void onResponse(Call<MostPopularDataModel> call, Response<MostPopularDataModel> response) {

                    if (response.code() != 200) {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    }
                    else {
                        if (response.body() != null) {
                            moviesArrayList = new ArrayList<MostPopularDataDetailModel>(response.body().getItems());
                            moviesRecyclerView = (RecyclerView) findViewById(R.id.movies);
                            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
                            moviesRecyclerView.setLayoutManager(mLayoutManager);
                            moviesListAdapter = new MoviesListAdapter(moviesArrayList, mContext);
                            moviesRecyclerView.setAdapter(moviesListAdapter);
                        }
                    }
                }

                @Override
                public void onFailure(Call<MostPopularDataModel> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }
}