package com.example.android_tv_show_notifier.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_tv_show_notifier.fragments.NetworkAvailabilityDialogFragment;
import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.adapters.MostPopularMoviesListAdapter;
import com.example.android_tv_show_notifier.adapters.NewMoviesListAdapter;
import com.example.android_tv_show_notifier.adapters.SearchTitleListAdapter;
import com.example.android_tv_show_notifier.api.ImdbAPI;
import com.example.android_tv_show_notifier.api.RetrofitInstance;
import com.example.android_tv_show_notifier.models.MostPopularDataDetailModel;
import com.example.android_tv_show_notifier.models.MostPopularDataModel;
import com.example.android_tv_show_notifier.models.NewMovieDataDetailModel;
import com.example.android_tv_show_notifier.models.NewMovieDataModel;
import com.example.android_tv_show_notifier.models.SearchDataModel;
import com.example.android_tv_show_notifier.models.SearchResultsModel;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
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
    private SearchTitleListAdapter searchTitleListAdapter;
    private NewMoviesListAdapter newMoviesListAdapter;
    private Context mContext;
    private Call<MostPopularDataModel> MostPopularTVsAPICall;
    private Call<MostPopularDataModel> MostPopularMoviesAPICall;
    private Call<NewMovieDataModel> InTheatersAPICall;
    private Call<NewMovieDataModel> ComingSoonAPICall;
    private Call<SearchDataModel> SearchTitleAPICall;
    private ImdbAPI imdbAPI;
    private Toolbar toolbar;
    private Button btSignIn;
    private FirebaseAuth firebaseAuth;
    private AuthCredential authCredential;
    private FirebaseUser firebaseUser;
    private NetworkAvailabilityDialogFragment networkAvailabilityDialogFragment;

    private SignInClient oneTapClient;
    private BeginSignInRequest signInRequest;
    private static final int REQ_ONE_TAP = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.mContext = getApplicationContext();
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }
        this.moviesRecyclerView = (RecyclerView) findViewById(R.id.vertical_recycler_view);
        this.moviesRecyclerView.setLayoutManager(mLayoutManager);
        this.networkAvailabilityDialogFragment = new NetworkAvailabilityDialogFragment();
        if (!this.networkAvailabilityDialogFragment.isNetworkAvailable(this.mContext)) {
            FragmentManager fm = getSupportFragmentManager();
            this.networkAvailabilityDialogFragment.show(fm, NetworkAvailabilityDialogFragment.TAG);
            fm.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                @Override
                public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                    super.onFragmentViewDestroyed(fm, f);

                    imdbAPI = new RetrofitInstance().api;
                    firebaseAuth = FirebaseAuth.getInstance();
                    oneTapClient = Identity.getSignInClient(mContext);
                    setToolbar();
                    getMostPopularTVs();
                    setGoogleSignIn();

                    fm.unregisterFragmentLifecycleCallbacks(this);
                }
            }, false);
        }
        else {
            this.imdbAPI = new RetrofitInstance().api;
            this.firebaseAuth = FirebaseAuth.getInstance();
            oneTapClient = Identity.getSignInClient(this);
            setToolbar();
            getMostPopularTVs();
            setGoogleSignIn();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search_menu, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        ImageView searchStartIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        ImageView searchCloseIcon = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        TextView searchTextView = searchView.findViewById(androidx.appcompat.R.id.search_src_text);

        searchStartIcon.setColorFilter(getResources().getColor(R.color.color_text));
        searchCloseIcon.setColorFilter(getResources().getColor(R.color.color_text));
        searchTextView.setTextColor(getResources().getColor(R.color.color_text));

        searchView.setQueryHint(getString(R.string.search_for_all_titles));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                getSearchData(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.isEmpty() && moviesRecyclerView.getAdapter().getClass() == SearchTitleListAdapter.class) {
                    if (toolbar.getTitle() == getString(R.string.top_tv)) {
                        moviesRecyclerView.setAdapter(mostPopularMoviesListAdapter);
                    } else if (toolbar.getTitle() == getString(R.string.top_movies)){
                        moviesRecyclerView.setAdapter(mostPopularMoviesListAdapter);
                    } else if (toolbar.getTitle() == getString(R.string.in_theaters)){
                        moviesRecyclerView.setAdapter(newMoviesListAdapter);
                    } else if (toolbar.getTitle() == getString(R.string.coming_soon)){
                        moviesRecyclerView.setAdapter(newMoviesListAdapter);
                    }
                }
                return false;
            }

        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_ONE_TAP:
                try {
                    SignInCredential credential = oneTapClient.getSignInCredentialFromIntent(data);
                    String idToken = credential.getGoogleIdToken();
                    AuthCredential credentials = GoogleAuthProvider.getCredential(idToken,null);
                    if (idToken !=  null) {
                        firebaseAuth.signInWithCredential(credentials).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
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
                } catch (ApiException e) {
                    displayToast(e.getLocalizedMessage());
                }
                break;
        }
    }

    public void setupOneTapClient() {
        signInRequest = BeginSignInRequest.builder()
                .setPasswordRequestOptions(BeginSignInRequest.PasswordRequestOptions.builder()
                        .setSupported(true)
                        .build())
                .setGoogleIdTokenRequestOptions(BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                        .setSupported(true)
                        .setServerClientId(getString(R.string.default_web_client_id))
                        .setFilterByAuthorizedAccounts(false)
                        .build())
                .setAutoSelectEnabled(true)
                .build();
        oneTapClient.beginSignIn(signInRequest)
                .addOnSuccessListener(this, new OnSuccessListener<BeginSignInResult>() {
                    @Override
                    public void onSuccess(BeginSignInResult result) {
                        try {
                            startIntentSenderForResult(result.getPendingIntent().getIntentSender(), REQ_ONE_TAP, null, 0, 0, 0);
                        } catch (IntentSender.SendIntentException e) {
                            displayToast("Couldn't start One Tap UI: " + e.getLocalizedMessage());
                        }
                    }
                })
                .addOnFailureListener(this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        displayToast(e.getLocalizedMessage());
                    }
                });
    }

    public void setGoogleSignIn() {
        btSignIn = navigationView.getHeaderView(0).findViewById(R.id.bt_sign_in);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null) {
            btSignIn.setText(firebaseUser.getDisplayName() + " (log out)");
            setLogOutOnClickListener();
        }
        else {
            btSignIn.setText(R.string.log_in);
            setLogInOnClickListener();
        }
    }

    public void setLogInOnClickListener() {
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!networkAvailabilityDialogFragment.isNetworkAvailable(mContext)) {
                    FragmentManager fm = getSupportFragmentManager();
                    networkAvailabilityDialogFragment.show(fm, NetworkAvailabilityDialogFragment.TAG);
                    fm.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                        @Override
                        public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                            super.onFragmentViewDestroyed(fm, f);

                            if (networkAvailabilityDialogFragment.isNetworkAvailable(mContext)) setupOneTapClient();

                            fm.unregisterFragmentLifecycleCallbacks(this);
                        }
                    }, false);
                }
                else {
                    setupOneTapClient();
                }
            }
        });
    }

    public void setLogOutOnClickListener() {
        btSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!networkAvailabilityDialogFragment.isNetworkAvailable(mContext)) {
                    FragmentManager fm = getSupportFragmentManager();
                    networkAvailabilityDialogFragment.show(fm, NetworkAvailabilityDialogFragment.TAG);
                    fm.registerFragmentLifecycleCallbacks(new FragmentManager.FragmentLifecycleCallbacks() {
                        @Override
                        public void onFragmentViewDestroyed(@NonNull FragmentManager fm, @NonNull Fragment f) {
                            super.onFragmentViewDestroyed(fm, f);

                            if (networkAvailabilityDialogFragment.isNetworkAvailable(mContext)) {
                                oneTapClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
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

                            fm.unregisterFragmentLifecycleCallbacks(this);
                        }
                    }, false);
                }
                else {
                    oneTapClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
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
                            setMostPopularMoviesListAdapter(new ArrayList<MostPopularDataDetailModel>(response.body().getItems()));
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
                            setMostPopularMoviesListAdapter(new ArrayList<MostPopularDataDetailModel>(response.body().getItems()));
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
                            setNewMoviesListAdapter(new ArrayList<NewMovieDataDetailModel>(response.body().getItems()));
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
                            setNewMoviesListAdapter(new ArrayList<NewMovieDataDetailModel>(response.body().getItems()));
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

    public void getSearchData(String searchEpression) {
        this.SearchTitleAPICall = imdbAPI.getSearchData(searchEpression);
        try {
            SearchTitleAPICall.enqueue(new Callback<SearchDataModel>() {
                @Override
                public void onResponse(Call<SearchDataModel> call, Response<SearchDataModel> response) {

                    if (response.code() != 200) {
                        displayToast(response.message());
                    }
                    else {
                        if (response.body() != null) {
                            ArrayList<SearchResultsModel> results = new ArrayList<SearchResultsModel>(response.body().getResults());
                            if (results.size() > 0) {
                                setSearchDataListAdapter(new ArrayList<SearchResultsModel>(response.body().getResults()));
                            }
                            else {
                                displayToast(getString(R.string.no_results));
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<SearchDataModel> call, Throwable t) {
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
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer , toolbar , R.string.navigation_drawer_open , R.string.navigation_drawer_close );
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.color_text));
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
        if (!this.networkAvailabilityDialogFragment.isNetworkAvailable(this.mContext)) {
            this.networkAvailabilityDialogFragment.show(getSupportFragmentManager(), NetworkAvailabilityDialogFragment.TAG);
            return false;
        }
        else {
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
                favouritesIntent.putExtra("nav_bar_title", this.toolbar.getTitle());
                this.mContext.startActivity(favouritesIntent);
            }

            DrawerLayout drawer = findViewById(R.id. drawer_layout ) ;
            drawer.closeDrawer(GravityCompat. START ) ;
            return true;
        }
    }

    public void setMostPopularMoviesListAdapter(ArrayList<MostPopularDataDetailModel> arrayList) {
        mostPopularMoviesListAdapter = new MostPopularMoviesListAdapter(arrayList, mContext);
        moviesRecyclerView.setAdapter(mostPopularMoviesListAdapter);
    }

    public void setSearchDataListAdapter(ArrayList<SearchResultsModel> arrayList) {
        searchTitleListAdapter = new SearchTitleListAdapter(arrayList, mContext);
        moviesRecyclerView.setAdapter(searchTitleListAdapter);
    }

    public void setNewMoviesListAdapter(ArrayList<NewMovieDataDetailModel> arrayList) {
        newMoviesListAdapter = new NewMoviesListAdapter(arrayList, mContext);
        moviesRecyclerView.setAdapter(newMoviesListAdapter);
    }

    public void displayToast(String s) {
        Toast.makeText(mContext,s,Toast.LENGTH_SHORT).show();
    }
}