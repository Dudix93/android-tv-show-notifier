package com.example.android_tv_show_notifier.adapters;

import androidx.fragment.app.FragmentManager;
import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.android_tv_show_notifier.Database.RoomDB;
import com.example.android_tv_show_notifier.Entities.FavouriteActorEntity;
import com.example.android_tv_show_notifier.Entities.FavouriteTitleEntity;
import com.example.android_tv_show_notifier.fragments.FavouriteActorsFragment;
import com.example.android_tv_show_notifier.fragments.FavouriteTitlesFragment;
import com.example.android_tv_show_notifier.models.ActorModel;
import com.example.android_tv_show_notifier.models.NameModel;
import com.example.android_tv_show_notifier.models.TitleModel;

import java.util.ArrayList;

public class FavouritesPagerAdapter extends FragmentPagerAdapter {

    int totalTabs;
    private ArrayList<FavouriteTitleEntity> favouriteTitlesArrayList;
    private ArrayList<FavouriteActorEntity> favouriteActorsArrayList;
    private FavouriteTitlesFragment favouriteTitlesFragment;
    private FavouriteActorsFragment favouriteActorsFragment;
    private RoomDB roomDB;

    public FavouritesPagerAdapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        this.totalTabs = totalTabs;
        this.roomDB = RoomDB.getInstance(context);
        this.favouriteTitlesArrayList = new ArrayList<FavouriteTitleEntity>(this.roomDB.favouriteTitleDao().getAll());
        this.favouriteActorsArrayList = new ArrayList<FavouriteActorEntity>(this.roomDB.favouriteActorDao().getAll());
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                this.favouriteTitlesFragment = new FavouriteTitlesFragment(this.favouriteTitlesArrayList);
                return this.favouriteTitlesFragment;
            case 1:
                this.favouriteActorsFragment = new FavouriteActorsFragment(this.favouriteActorsArrayList);
                return this.favouriteActorsFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
