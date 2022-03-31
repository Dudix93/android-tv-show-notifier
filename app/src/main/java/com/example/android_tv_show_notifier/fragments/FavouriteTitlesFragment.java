package com.example.android_tv_show_notifier.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_tv_show_notifier.Entities.FavouriteTitleEntity;
import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.adapters.FavouriteTitlesListAdapter;

import java.util.ArrayList;

public class FavouriteTitlesFragment extends Fragment {

    private View layoutView;
    private FavouriteTitlesListAdapter titlesListAdapter;
    private ArrayList<FavouriteTitleEntity> favouriteTitlesArrayList;
    private RecyclerView moviesRecyclerView;

    public FavouriteTitlesFragment(ArrayList<FavouriteTitleEntity> favouriteTitlesArrayList) {
        this.favouriteTitlesArrayList = favouriteTitlesArrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.layoutView = inflater.inflate(R.layout.movies_recycler_view, container, false);
        this.moviesRecyclerView = this.layoutView.findViewById(R.id.movies_recycler_view);
        this.titlesListAdapter = new FavouriteTitlesListAdapter(this.favouriteTitlesArrayList, getContext());
        this.moviesRecyclerView.setAdapter(titlesListAdapter);
        return layoutView;
    }
}
