package com.example.android_tv_show_notifier.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_tv_show_notifier.DownloadImageFromUrl;
import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.adapters.ActorsListAdapter;
import com.example.android_tv_show_notifier.adapters.MoviesListAdapter;
import com.example.android_tv_show_notifier.api.ImdbAPI;
import com.example.android_tv_show_notifier.api.RetrofitInstance;
import com.example.android_tv_show_notifier.models.ActorModel;
import com.example.android_tv_show_notifier.models.MostPopularDataDetailModel;
import com.example.android_tv_show_notifier.models.TitleModel;
import com.example.android_tv_show_notifier.models.TrailerModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TitleActivity extends AppCompatActivity {
    
    private String titleId;
    private Bundle intentExtras;
    private Call<TitleModel> TitleAPICall;
    private Call<TrailerModel> TrailerAPICall;
    private ImdbAPI imdbAPI;
    private TextView titleNameTextView;
    private TextView releaseYearTextView;
    private TextView ratingTextView;
    private TextView plotTextView;
    private ImageView posterImageView;
    private ImageView trailerThumbnailImageView;
    private ImageView playIconImageView;
    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private TitleModel titleModel;
    private TrailerModel trailerModel;
    private Drawable trailerThumbnailDrawable;
    private RecyclerView actorsRecyclerView;
    private ArrayList<ActorModel> actorsArrayList;
    private ActorsListAdapter actorsListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        this.intentExtras = getIntent().getExtras();
        this.titleNameTextView = findViewById(R.id.title_name);
        this.releaseYearTextView = findViewById(R.id.title_year);
        this.ratingTextView = findViewById(R.id.title_rating);
        this.plotTextView = findViewById(R.id.title_plot);
        this.posterImageView = findViewById(R.id.movie_poster);
        this.collapsingToolbar = findViewById(R.id.collapsing_toolbar);
        this.trailerThumbnailImageView = findViewById(R.id.trailer_thumbnail);
        fillTitleData();
    }

    public void getTitleData(String titleId) {
        try {
            TitleAPICall.enqueue(new Callback<TitleModel>() {
                @Override
                public void onResponse(Call<TitleModel> call, Response<TitleModel> response) {

                    if (response.code() != 200) {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    }
                    else {
                        if (response.body() != null) {
                            titleModel = response.body();
                            getTrailer(titleModel.getId());
                        }
                    }
                }

                @Override
                public void onFailure(Call<TitleModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }

    public void getTrailer(String titleId) {
        try {
            TrailerAPICall.enqueue(new Callback<TrailerModel>() {
                @Override
                public void onResponse(Call<TrailerModel> call, Response<TrailerModel> response) {

                    if (response.code() != 200) {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    }
                    else {
                        if (response.body() != null) {
                            trailerModel = response.body();
                            setTitleInfo();
                        }
                    }
                }

                @Override
                public void onFailure(Call<TrailerModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }

    public void fillTitleData() {
        if (this.intentExtras != null) {
            if (intentExtras.containsKey("title_id")) {
                this.titleId = this.intentExtras.getString("title_id");
                this.imdbAPI = new RetrofitInstance().api;
                this.TitleAPICall = imdbAPI.getTitle(this.titleId);
                this.TrailerAPICall = imdbAPI.getTrailer(this.titleId);
                getTitleData(this.titleId);
            }
        }
    }

    public void setToolbar() {
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getApplicationContext(), com.google.android.material.R.color.design_default_color_on_primary));
        collapsingToolbar.setTitleEnabled(true);
        if (toolbar != null) {
            ((AppCompatActivity) TitleActivity.this).setSupportActionBar(toolbar);

            ActionBar actionBar = ((AppCompatActivity) TitleActivity.this).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        }
    }

    public void setTitleInfo() {
        titleNameTextView.setText(titleModel.getTitle());
        releaseYearTextView.setText(titleModel.getYear());
        ratingTextView.setText(titleModel.getImDbRating());
        plotTextView.setText(titleModel.getPlot());
        new DownloadImageFromUrl(posterImageView).execute(titleModel.getImage());
        new ThumbnailAsyncTask(this).execute();
        setToolbar();
        trailerThumbnailImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(trailerModel.getLink())));
            }
        });
        setActorsList();
    }

    public void setActorsList() {
        actorsArrayList = new ArrayList<ActorModel>(titleModel.getActorList());
        actorsRecyclerView = (RecyclerView) findViewById(R.id.actors_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        actorsRecyclerView.setLayoutManager(mLayoutManager);
        actorsListAdapter = new ActorsListAdapter(actorsArrayList, getApplicationContext());
        actorsRecyclerView.setAdapter(actorsListAdapter);    
    }
    
    private static class ThumbnailAsyncTask extends AsyncTask<String, Integer, Drawable> {

        private WeakReference<TitleActivity> activityReference;

        ThumbnailAsyncTask(TitleActivity context) {
            activityReference = new WeakReference<>(context);
        }

        @Override
        protected Drawable doInBackground(String... strings) {
            Bitmap bmp = null;
            TitleActivity activity = activityReference.get();
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(activity.trailerModel.getThumbnailUrl()).openConnection();
                connection.connect();
                InputStream input = connection.getInputStream();
                bmp = BitmapFactory.decodeStream(input);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return new BitmapDrawable(activity.getResources(), Bitmap.createScaledBitmap(bmp,
                    activity.getWindow().getDecorView().getWidth(),
                    500,
                    true));
        }

        protected void onPostExecute(Drawable result) {
            TitleActivity activity = activityReference.get();
            ImageView trailerThumbnailImageView = activity.findViewById(R.id.trailer_thumbnail);
            Drawable[] layers = new Drawable[2];
            layers[0] = result;
            layers[1] = ResourcesCompat.getDrawable(activity.getResources(), R.drawable.ic_play, null);
            LayerDrawable layerDrawable = new LayerDrawable(layers);
            trailerThumbnailImageView.setImageDrawable(layerDrawable);
        }
    }
}
