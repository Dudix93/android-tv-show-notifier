package com.example.android_tv_show_notifier.activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_tv_show_notifier.DownloadImageFromUrl;
import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.api.ImdbAPI;
import com.example.android_tv_show_notifier.api.RetrofitInstance;
import com.example.android_tv_show_notifier.models.TitleModel;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TitleActivity extends AppCompatActivity {
    
    private String titleId;
    private Bundle intentExtras;
    private Call<TitleModel> TitleAPICall;
    private ImdbAPI imdbAPI;
    private TextView releaseYearTextView;
    private TextView ratingTextView;
    private TextView plotTextView;
    private ImageView posterImageView;
    private CollapsingToolbarLayout collapsingToolbar;
    private Toolbar toolbar;
    private TitleModel titleModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_title);
        this.intentExtras = getIntent().getExtras();
        this.releaseYearTextView = findViewById(R.id.title_year);
        this.ratingTextView = findViewById(R.id.title_rating);
        this.plotTextView = findViewById(R.id.title_plot);
        this.posterImageView = findViewById(R.id.movie_poster);
        this.collapsingToolbar = findViewById(R.id.collapsing_toolbar);
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
                            releaseYearTextView.setText(titleModel.getYear());
                            ratingTextView.setText(titleModel.getImDbRating());
                            plotTextView.setText(titleModel.getPlot());
                            new DownloadImageFromUrl(posterImageView).execute(titleModel.getImage());
                            setToolbar();
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

    public void fillTitleData() {
        if (this.intentExtras != null) {
            if (intentExtras.containsKey("title_id")) {
                this.titleId = this.intentExtras.getString("title_id");
                this.imdbAPI = new RetrofitInstance().api;
                this.TitleAPICall = imdbAPI.getTitle(this.titleId);
                getTitleData(this.titleId);
            }
        }
    }

    public void setToolbar() {
        collapsingToolbar.setContentScrimColor(ContextCompat.getColor(getApplicationContext(), com.google.android.material.R.color.design_default_color_on_primary));
        collapsingToolbar.setTitle(this.titleModel.getTitle());
        collapsingToolbar.setTitleEnabled(true);
        if (toolbar != null) {
            ((AppCompatActivity) TitleActivity.this).setSupportActionBar(toolbar);

            ActionBar actionBar = ((AppCompatActivity) TitleActivity.this).getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
            }
        } else {
            // Don't inflate. Tablet is in landscape mode.
        }
    }
}
