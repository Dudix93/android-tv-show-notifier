package com.example.android_tv_show_notifier.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_tv_show_notifier.Entities.FavouriteActorEntity;
import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.adapters.ActorsVerticalListAdapter;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActorsFragment extends Fragment {

    private View layoutView;
    private ActorsVerticalListAdapter actorsListAdapter;
    private List<FavouriteActorEntity> favouriteActorsArrayList;
    private RecyclerView moviesRecyclerView;

    public FavouriteActorsFragment(List<FavouriteActorEntity> favouriteActorsArrayList) {
        this.favouriteActorsArrayList = favouriteActorsArrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.layoutView = inflater.inflate(R.layout.vertical_recycler_view, container, false);
        this.moviesRecyclerView = this.layoutView.findViewById(R.id.vertical_recycler_view);
        this.actorsListAdapter = new ActorsVerticalListAdapter(this.favouriteActorsArrayList, getContext());
        this.moviesRecyclerView.setAdapter(this.actorsListAdapter);
        return layoutView;
    }
}
