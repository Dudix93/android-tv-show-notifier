package com.example.android_tv_show_notifier.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.android_tv_show_notifier.Database.FirebaseDB;
import com.example.android_tv_show_notifier.Database.RoomDB;
import com.example.android_tv_show_notifier.Entities.FavouriteActorEntity;
import com.example.android_tv_show_notifier.Entities.FavouriteTitleEntity;
import com.example.android_tv_show_notifier.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.android_tv_show_notifier.adapters.FavouritesPagerAdapter;
import com.example.android_tv_show_notifier.fragments.FavouriteActorsFragment;
import com.example.android_tv_show_notifier.fragments.FavouriteTitlesFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {

    private FavouriteTitlesFragment favouriteTitlesFragment;
    private FavouriteActorsFragment favouriteActorsFragment;
    private BottomNavigationView bottomNavigationView;
    private List<FavouriteTitleEntity> favouriteTitlesArrayList;
    private List<FavouriteActorEntity> favouriteActorsArrayList;
    private FirebaseUser user;
    private FirebaseDB firebaseDB;
    private RoomDB roomDB;
    private FragmentManager fragmentManager;
    private NavigationBarView.OnItemSelectedListener onItemSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favourites_tab_layout);
        bottomNavigationView = findViewById(R.id.bottom_navigation_favourites);
        favouriteTitlesArrayList = new ArrayList<FavouriteTitleEntity>();
        favouriteActorsArrayList = new ArrayList<FavouriteActorEntity>();
        fragmentManager = getSupportFragmentManager();
        setupToolbarNavIcon();
        loadFavouriteTitles();
    }

    public void setupToolbarNavIcon() {
        Toolbar toolbar = findViewById(R.id.favourites_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void loadFavouriteTitles() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            firebaseDB = new FirebaseDB(user.getUid());
            firebaseDB.getFavouriteTitles(new FirebaseDB.DataCallback() {
                @Override
                public void callback(DataSnapshot snapshot) {
                    for (DataSnapshot sshot : snapshot.getChildren()) {
                        HashMap value = (HashMap) sshot.getValue();
                        favouriteTitlesArrayList.add(new FavouriteTitleEntity(
                                (String) value.get("titleId"),
                                (String) value.get("title"),
                                (Long) value.get("releaseYear"),
                                (String) value.get("posterUrl")
                        ));
                    }
                    favouriteTitlesFragment = new FavouriteTitlesFragment(favouriteTitlesArrayList);
                    loadFavouriteActors();
                }
            });
        }
        else if (user == null) {
            roomDB = RoomDB.getInstance(getApplicationContext());
            favouriteTitlesArrayList = roomDB.favouriteTitleDao().getAll();
            bottomNavigationView.setSelectedItemId(R.id.favourite_titles);
            favouriteTitlesFragment = new FavouriteTitlesFragment(favouriteTitlesArrayList);
            loadFavouriteActors();
        }
    }

    public void loadFavouriteActors() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            firebaseDB = new FirebaseDB(user.getUid());
            firebaseDB.getFavouriteActors(new FirebaseDB.DataCallback() {
                @Override
                public void callback(DataSnapshot snapshot) {
                    for (DataSnapshot sshot : snapshot.getChildren()) {
                        HashMap value = (HashMap) sshot.getValue();
                        favouriteActorsArrayList.add(new FavouriteActorEntity(
                                (String) value.get("actorId"),
                                (String) value.get("name"),
                                (String) value.get("photoUrl")
                        ));
                    }
                    favouriteActorsFragment = new FavouriteActorsFragment(favouriteActorsArrayList);
                    setOnItemSelectedListener(bottomNavigationView);
                }
            });
        }
        else if (user == null) {
            roomDB = RoomDB.getInstance(getApplicationContext());
            favouriteActorsArrayList = roomDB.favouriteActorDao().getAll();
            bottomNavigationView.setSelectedItemId(R.id.favourite_actors);
            favouriteActorsFragment = new FavouriteActorsFragment(favouriteActorsArrayList);
            setOnItemSelectedListener(bottomNavigationView);
        }
    }

    public void setOnItemSelectedListener(BottomNavigationView bnv) {
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()) {
                    case R.id.favourite_titles:
                        fragmentManager.beginTransaction().replace(R.id.favourites_fragment, favouriteTitlesFragment).commit();
                        return true;
                    case R.id.favourite_actors:
                        fragmentManager.beginTransaction().replace(R.id.favourites_fragment, favouriteActorsFragment).commit();
                        return true;
                }
                return false;
            }
        });
        bnv.setSelectedItemId(R.id.favourite_titles);
    }
}
