package com.example.android_tv_show_notifier;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder> {

    private ArrayList<MostPopularDataDetailModel> mMovies = new ArrayList<MostPopularDataDetailModel>();
    private Context mContext;

    public MoviesListAdapter(ArrayList<MostPopularDataDetailModel> movies, Context context) {
        this.mMovies = movies;
        this.mContext = context;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView releaseYearTextView;
        ImageView moviePosterImageView;

        public MovieViewHolder(View view) {
            super(view);
            this.titleTextView = view.findViewById(R.id.title);
            this.releaseYearTextView = view.findViewById(R.id.release_year);
            this.moviePosterImageView = view.findViewById(R.id.movie_poster);
        }

        public TextView getTitleTextView() {
            return this.titleTextView;
        }
        public TextView getReleaseYearTextView() { return this.releaseYearTextView; }
        public ImageView getMoviePosterImageView() { return this.moviePosterImageView; }
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup viewGroup, int viwType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder viewHolder, final int position) {
        viewHolder.getTitleTextView().setText(this.mMovies.get(position).getTitle());
        viewHolder.getReleaseYearTextView().setText(this.mMovies.get(position).getYear());
        Glide.with(this.mContext)
                .load(this.mMovies.get(position).getImage())
                .into(viewHolder.getMoviePosterImageView());
    }

    @Override
    public int getItemCount() {
        return this.mMovies.size();
    }
}
