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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.adapters.MoviesListAdapter;
import com.example.android_tv_show_notifier.api.ImdbAPI;
import com.example.android_tv_show_notifier.api.RetrofitInstance;
import com.example.android_tv_show_notifier.models.MostPopularDataDetailModel;
import com.example.android_tv_show_notifier.models.MostPopularDataModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ArrayList<MostPopularDataDetailModel> moviesArrayList;
    private RecyclerView moviesRecyclerView;
    private RecyclerView.Adapter moviesListAdapter;
    private Context mContext;
    private Call<MostPopularDataModel> MostPopularTVsAPICall;
    private ImdbAPI imdbAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = getApplicationContext();
        this.imdbAPI = new RetrofitInstance().api;
        this.MostPopularTVsAPICall = imdbAPI.getMostPopularTVs();
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        setToolbar();
        getMostPopularTVs();
    }

    public void getMostPopularTVs() {
        try {
            MostPopularTVsAPICall.enqueue(new Callback<MostPopularDataModel>() {
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

    public void setToolbar() {
        Toolbar toolbar = findViewById(R.id. toolbar ) ;
        setSupportActionBar(toolbar) ;
        DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
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

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        int id = item.getItemId() ;
        return super.onOptionsItemSelected(item) ;
    }

    @SuppressWarnings ( "StatementWithEmptyBody" )
    @Override
    public boolean onNavigationItemSelected ( @NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId() ;
        if (id == R.id. nav_camera ) {
            // Handle the camera action
        } else if (id == R.id. nav_gallery ) {
        } else if (id == R.id. nav_slideshow ) {
        } else if (id == R.id. nav_manage ) {
        } else if (id == R.id. nav_share ) {
        } else if (id == R.id. nav_send ) {
        }
        DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
        drawer.closeDrawer(GravityCompat. START ) ;
        return true;
    }
}