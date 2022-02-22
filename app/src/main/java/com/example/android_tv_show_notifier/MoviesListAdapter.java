package com.example.android_tv_show_notifier;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder> {

    private ArrayList<MostPopularDataDetailModel> mMovies = new ArrayList<MostPopularDataDetailModel>();

    public MoviesListAdapter(ArrayList<MostPopularDataDetailModel> movies) {
        this.mMovies = movies;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;

        public MovieViewHolder(View view) {
            super(view);
            this.titleTextView = view.findViewById(R.id.title);
        }

        public TextView getTitleTextView() {
            return this.titleTextView;
        }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viwType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_todo, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder viewHolder, final int position) {
        viewHolder.getTitleTextView().setText(this.mMovies.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return this.mMovies.size();
    }
}
