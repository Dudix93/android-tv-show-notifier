package com.example.android_tv_show_notifier.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.adapters.MostPopularMoviesListAdapter;
import com.example.android_tv_show_notifier.adapters.NewMoviesListAdapter;
import com.example.android_tv_show_notifier.api.ImdbAPI;
import com.example.android_tv_show_notifier.api.RetrofitInstance;
import com.example.android_tv_show_notifier.models.MostPopularDataDetailModel;
import com.example.android_tv_show_notifier.models.MostPopularDataModel;
import com.example.android_tv_show_notifier.models.NewMovieDataDetailModel;
import com.example.android_tv_show_notifier.models.NewMovieDataModel;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<MostPopularDataDetailModel> moviesArrayList;
    private RecyclerView moviesRecyclerView;
    private MostPopularMoviesListAdapter mostPopularMoviesListAdapter;
    private NewMoviesListAdapter newMoviesListAdapter;
    private Context mContext;
    private Call<MostPopularDataModel> MostPopularTVsAPICall;
    private Call<MostPopularDataModel> MostPopularMoviesAPICall;
    private Call<NewMovieDataModel> InTheatersAPICall;
    private Call<NewMovieDataModel> ComingSoonAPICall;
    private ImdbAPI imdbAPI;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = getApplicationContext();
        this.imdbAPI = new RetrofitInstance().api;
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        moviesRecyclerView = (RecyclerView) findViewById(R.id.movies);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        moviesRecyclerView.setLayoutManager(mLayoutManager);
        setToolbar();
        getMostPopularTVs();
    }

    public void getMostPopularTVs() {
        this.MostPopularTVsAPICall = imdbAPI.getMostPopularTVs();
        try {
            MostPopularTVsAPICall.enqueue(new Callback<MostPopularDataModel>() {
                @Override
                public void onResponse(Call<MostPopularDataModel> call, Response<MostPopularDataModel> response) {

                    if (response.code() != 200) {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    }
                    else {
                        if (response.body() != null) {
                            setMostPopularMoviesListAdapterListAdapter(new ArrayList<MostPopularDataDetailModel>(response.body().getItems()));
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

    public void getMostPopularMovies() {
        this.MostPopularMoviesAPICall = imdbAPI.getMostPopularMovies();
        try {
            MostPopularMoviesAPICall.enqueue(new Callback<MostPopularDataModel>() {
                @Override
                public void onResponse(Call<MostPopularDataModel> call, Response<MostPopularDataModel> response) {

                    if (response.code() != 200) {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    }
                    else {
                        if (response.body() != null) {
                            setMostPopularMoviesListAdapterListAdapter(new ArrayList<MostPopularDataDetailModel>(response.body().getItems()));
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

    public void getInTheaters() {
        this.InTheatersAPICall = imdbAPI.getInTheaters();
        try {
            InTheatersAPICall.enqueue(new Callback<NewMovieDataModel>() {
                @Override
                public void onResponse(Call<NewMovieDataModel> call, Response<NewMovieDataModel> response) {

                    if (response.code() != 200) {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    }
                    else {
                        if (response.body() != null) {
                            setNewMoviesListAdapterListAdapter(new ArrayList<NewMovieDataDetailModel>(response.body().getItems()));
                        }
                    }
                }

                @Override
                public void onFailure(Call<NewMovieDataModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }

    public void getComingSoon() {
        this.ComingSoonAPICall = imdbAPI.getComingSoon();
        try {
            ComingSoonAPICall.enqueue(new Callback<NewMovieDataModel>() {
                @Override
                public void onResponse(Call<NewMovieDataModel> call, Response<NewMovieDataModel> response) {

                    if (response.code() != 200) {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    }
                    else {
                        if (response.body() != null) {
                            setNewMoviesListAdapterListAdapter(new ArrayList<NewMovieDataDetailModel>(response.body().getItems()));
                        }
                    }
                }

                @Override
                public void onFailure(Call<NewMovieDataModel> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG);
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }

    public void setToolbar() {
        toolbar = findViewById(R.id. toolbar );
        setSupportActionBar(toolbar) ;
        DrawerLayout drawer = findViewById(R.id. drawer_layout );
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer , toolbar , R.string.navigation_drawer_open ,
                R.string.navigation_drawer_close ) ;
        drawer.addDrawerListener(toggle) ;
        toggle.syncState() ;
        NavigationView navigationView = findViewById(R.id. nav_view ) ;
        navigationView.setNavigationItemSelectedListener( this ) ;
    }

    @Override
    public void onBackPressed () {
        DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
        if (drawer.isDrawerOpen(GravityCompat. START )) {
            drawer.closeDrawer(GravityCompat. START ) ;
        } else {
            super .onBackPressed() ;
        }
    }

    @SuppressWarnings ( "StatementWithEmptyBody" )
    @Override
    public boolean onNavigationItemSelected ( @NonNull MenuItem item) {
        String nav_item = item.getTitle().toString();

        if (nav_item.equals(getResources().getString(R.string.in_theaters))) {
            getInTheaters();
            if (toolbar != null) toolbar.setTitle(R.string.in_theaters);
        }
        else if (nav_item.equals(getResources().getString(R.string.coming_soon))) {
            getComingSoon();
            if (toolbar != null) toolbar.setTitle(R.string.coming_soon);
        }
        else if (nav_item.equals(getResources().getString(R.string.top_movies))) {
            getMostPopularMovies();
            if (toolbar != null) toolbar.setTitle(R.string.top_movies);
        }
        else if (nav_item.equals(getResources().getString(R.string.top_tv))) {
            getMostPopularTVs();
            if (toolbar != null) toolbar.setTitle(R.string.top_tv);
        }

        DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
        drawer.closeDrawer(GravityCompat. START ) ;
        return true;
    }

    public void setMostPopularMoviesListAdapterListAdapter(ArrayList<MostPopularDataDetailModel> arrayList) {
        mostPopularMoviesListAdapter = new MostPopularMoviesListAdapter(arrayList, mContext);
        moviesRecyclerView.setAdapter(mostPopularMoviesListAdapter);
    }

    public void setNewMoviesListAdapterListAdapter(ArrayList<NewMovieDataDetailModel> arrayList) {
        newMoviesListAdapter = new NewMoviesListAdapter(arrayList, mContext);
        moviesRecyclerView.setAdapter(newMoviesListAdapter);
    }
}