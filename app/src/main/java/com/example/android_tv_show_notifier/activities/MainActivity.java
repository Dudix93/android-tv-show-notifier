package com.example.android_tv_show_notifier.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import com.example.android_tv_show_notifier.Database.RoomDB;
import com.example.android_tv_show_notifier.Entities.FavouriteActorEntity;
import com.example.android_tv_show_notifier.Entities.FavouriteTitleEntity;
import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.adapters.MostPopularMoviesListAdapter;
import com.example.android_tv_show_notifier.adapters.NewMoviesListAdapter;
import com.example.android_tv_show_notifier.adapters.TitlesListAdapter;
import com.example.android_tv_show_notifier.api.ImdbAPI;
import com.example.android_tv_show_notifier.api.RetrofitInstance;
import com.example.android_tv_show_notifier.models.NameModel;
import com.example.android_tv_show_notifier.models.MostPopularDataDetailModel;
import com.example.android_tv_show_notifier.models.MostPopularDataModel;
import com.example.android_tv_show_notifier.models.NewMovieDataDetailModel;
import com.example.android_tv_show_notifier.models.NewMovieDataModel;
import com.example.android_tv_show_notifier.models.TitleModel;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView moviesRecyclerView;
    private NavigationView navigationView;
    private MostPopularMoviesListAdapter mostPopularMoviesListAdapter;
    private NewMoviesListAdapter newMoviesListAdapter;
    private TitlesListAdapter titlesListAdapter;
    private Context mContext;
    private Call<MostPopularDataModel> MostPopularTVsAPICall;
    private Call<MostPopularDataModel> MostPopularMoviesAPICall;
    private Call<NewMovieDataModel> InTheatersAPICall;
    private Call<NewMovieDataModel> ComingSoonAPICall;
    private ImdbAPI imdbAPI;
    private Toolbar toolbar;
    private Button btSignIn;
    private GoogleSignInClient googleSignInClient;
    private FirebaseAuth firebaseAuth;
    private GoogleSignInOptions googleSignInOptions;
    private ActivityResultLauncher activityResultLauncher;
    private AuthCredential authCredential;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = getApplicationContext();
        this.imdbAPI = new RetrofitInstance().api;
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        this.moviesRecyclerView = (RecyclerView) findViewById(R.id.movies_recycler_view);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        this.moviesRecyclerView.setLayoutManager(mLayoutManager);
        this.firebaseAuth = FirebaseAuth.getInstance();
        setToolbar();
        getMostPopularTVs();
        setGoogleSignIn();
    }

    public void setGoogleSignIn() {
        btSignIn = navigationView.getHeaderView(0).findViewById(R.id.bt_sign_in);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            btSignIn.setText(firebaseUser.getDisplayName() + " (log out)");
        }
        else {
            btSignIn.setText(R.string.log_in);
        }
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(MainActivity.this ,googleSignInOptions);
        setActivityResultLauncher();
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent googleSignInIntent = googleSignInClient.getSignInIntent();
                activityResultLauncher.launch(googleSignInIntent);
            }
        });
    }

    public void setLogInOnClickListener() {
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent googleSignInIntent = googleSignInClient.getSignInIntent();
                activityResultLauncher.launch(googleSignInIntent);
            }
        });
    }

    public void setLogOutOnClickListener() {
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            firebaseAuth.signOut();
                            btSignIn.setText(R.string.log_in);
                            displayToast("Logout successful");
                            setLogInOnClickListener();
                        }
                    }
                });
            }
        });
    }

    public void setActivityResultLauncher() {
        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() {
            @Override
            public void onActivityResult(Object result) {
                if (((ActivityResult)result).getData() != null) {
                    Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(((ActivityResult)result).getData());

                    if(signInAccountTask.isSuccessful())
                    {
                        try {
                            GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                            if(googleSignInAccount != null)
                            {
                                authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(),null);
                                firebaseAuth.signInWithCredential(authCredential).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if(task.isSuccessful())
                                        {
                                            setLogOutOnClickListener();
                                            firebaseAuth = FirebaseAuth.getInstance();
                                            firebaseUser = firebaseAuth.getCurrentUser();
                                            if (firebaseUser != null)
                                            {
                                                displayToast("Hi " + firebaseUser.getDisplayName());
                                                btSignIn.setText(firebaseUser.getDisplayName() + " (log out)");
                                            }
                                        }
                                        else
                                        {
                                            displayToast("Authentication Failed :"+task.getException().getMessage());
                                        }
                                    }
                                });
                            }
                        }
                        catch (ApiException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
    }

    public void getMostPopularTVs() {
        this.MostPopularTVsAPICall = imdbAPI.getMostPopularTVs();
        try {
            MostPopularTVsAPICall.enqueue(new Callback<MostPopularDataModel>() {
                @Override
                public void onResponse(Call<MostPopularDataModel> call, Response<MostPopularDataModel> response) {

                    if (response.code() != 200) {
                        displayToast(response.message());
                    }
                    else {
                        if (response.body() != null) {
                            setMostPopularMoviesListAdapterListAdapter(new ArrayList<MostPopularDataDetailModel>(response.body().getItems()));
                        }
                    }
                }

                @Override
                public void onFailure(Call<MostPopularDataModel> call, Throwable t) {
                    displayToast(t.getMessage());
                }
            });
        } catch (Exception e) {
            displayToast(e.getMessage());
        }
    }

    public void getMostPopularMovies() {
        this.MostPopularMoviesAPICall = imdbAPI.getMostPopularMovies();
        try {
            MostPopularMoviesAPICall.enqueue(new Callback<MostPopularDataModel>() {
                @Override
                public void onResponse(Call<MostPopularDataModel> call, Response<MostPopularDataModel> response) {

                    if (response.code() != 200) {
                        displayToast(response.message());
                    }
                    else {
                        if (response.body() != null) {
                            setMostPopularMoviesListAdapterListAdapter(new ArrayList<MostPopularDataDetailModel>(response.body().getItems()));
                        }
                    }
                }

                @Override
                public void onFailure(Call<MostPopularDataModel> call, Throwable t) {
                    displayToast(t.getMessage());
                }
            });
        } catch (Exception e) {
            displayToast(e.getMessage());
        }
    }

    public void getInTheaters() {
        this.InTheatersAPICall = imdbAPI.getInTheaters();
        try {
            InTheatersAPICall.enqueue(new Callback<NewMovieDataModel>() {
                @Override
                public void onResponse(Call<NewMovieDataModel> call, Response<NewMovieDataModel> response) {

                    if (response.code() != 200) {
                        displayToast(response.message());
                    }
                    else {
                        if (response.body() != null) {
                            setNewMoviesListAdapterListAdapter(new ArrayList<NewMovieDataDetailModel>(response.body().getItems()));
                        }
                    }
                }

                @Override
                public void onFailure(Call<NewMovieDataModel> call, Throwable t) {
                    displayToast(t.getMessage());
                }
            });
        } catch (Exception e) {
            displayToast(e.getMessage());
        }
    }

    public void getComingSoon() {
        this.ComingSoonAPICall = imdbAPI.getComingSoon();
        try {
            ComingSoonAPICall.enqueue(new Callback<NewMovieDataModel>() {
                @Override
                public void onResponse(Call<NewMovieDataModel> call, Response<NewMovieDataModel> response) {

                    if (response.code() != 200) {
                        displayToast(response.message());
                    }
                    else {
                        if (response.body() != null) {
                            setNewMoviesListAdapterListAdapter(new ArrayList<NewMovieDataDetailModel>(response.body().getItems()));
                        }
                    }
                }

                @Override
                public void onFailure(Call<NewMovieDataModel> call, Throwable t) {
                    displayToast(t.getMessage());
                }
            });
        } catch (Exception e) {
            displayToast(e.getMessage());
        }
    }

    public void setToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer , toolbar , R.string.navigation_drawer_open ,
                R.string.navigation_drawer_close );
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed () {
        DrawerLayout drawer = findViewById(R.id. drawer_layout );
        if (drawer.isDrawerOpen(GravityCompat. START )) {
            drawer.closeDrawer(GravityCompat. START );
        } else {
            super.onBackPressed();
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
        else if (nav_item.equals(getResources().getString(R.string.favourites))) {
            Intent favouritesIntent = new Intent(this.mContext, FavouriteActivity.class);
            favouritesIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            this.mContext.startActivity(favouritesIntent);
            if (toolbar != null) toolbar.setTitle(R.string.favourites);
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

    public void displayToast(String s) {
        Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
    }
}