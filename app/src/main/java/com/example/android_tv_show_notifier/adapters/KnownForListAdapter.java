package com.example.android_tv_show_notifier.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.activities.TitleActivity;
import com.example.android_tv_show_notifier.fragments.NetworkAvailabilityDialogFragment;
import com.example.android_tv_show_notifier.models.KnownForModel;

import java.util.ArrayList;

public class KnownForListAdapter extends RecyclerView.Adapter<KnownForListAdapter.MovieViewHolder> {

    private ArrayList<KnownForModel> mMovies = new ArrayList<KnownForModel>();
    private Context mContext;

    public KnownForListAdapter(ArrayList<KnownForModel> movies, Context context) {
        this.mMovies = movies;
        this.mContext = context;
    }

    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        CardView titleCardView;
        TextView titleTextView;
        TextView releaseYearTextView;
        ImageView moviePosterImageView;

        public MovieViewHolder(View view) {
            super(view);
            this.titleCardView = view.findViewById(R.id.title_list_item);
            this.titleTextView = view.findViewById(R.id.title);
            this.releaseYearTextView = view.findViewById(R.id.release_year);
            this.moviePosterImageView = view.findViewById(R.id.movie_poster);
        }

        public CardView getTitleCardView() {
            return this.titleCardView;
        }
        public TextView getTitleTextView() { return this.titleTextView; }
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
                .apply(new RequestOptions().override(200, 300))
                .into(viewHolder.getMoviePosterImageView());

        viewHolder.getTitleCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent titleIntent = new Intent(view.getContext(), TitleActivity.class);
                titleIntent.putExtra("title_id", mMovies.get(viewHolder.getAdapterPosition()).getId());
                titleIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                new NetworkAvailabilityDialogFragment().checkNetwofkForNewIntent(mContext, view, titleIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mMovies.size();
    }
}
