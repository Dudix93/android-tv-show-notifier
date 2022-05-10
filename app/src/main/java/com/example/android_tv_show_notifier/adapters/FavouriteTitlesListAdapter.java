package com.example.android_tv_show_notifier.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_tv_show_notifier.Entities.FavouriteTitleEntity;
import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.activities.TitleActivity;

import java.util.ArrayList;
import java.util.List;

public class FavouriteTitlesListAdapter extends RecyclerView.Adapter<FavouriteTitlesListAdapter.TitleViewHolder> {

    private List<FavouriteTitleEntity> mTitles = new ArrayList<FavouriteTitleEntity>();
    private Context mContext;

    public FavouriteTitlesListAdapter(List<FavouriteTitleEntity> titles, Context context) {
        this.mTitles = titles;
        this.mContext = context;
    }

    public static class TitleViewHolder extends RecyclerView.ViewHolder {
        CardView titleCardView;
        TextView titleTextView;
        TextView releaseYearTextView;
        ImageView titlePosterImageView;

        public TitleViewHolder(View view) {
            super(view);
            this.titleCardView = view.findViewById(R.id.title_list_item);
            this.titleTextView = view.findViewById(R.id.title);
            this.releaseYearTextView = view.findViewById(R.id.release_year);
            this.titlePosterImageView = view.findViewById(R.id.movie_poster);
        }

        public CardView getTitleCardView() {
            return this.titleCardView;
        }
        public TextView getTitleTextView() { return this.titleTextView; }
        public TextView getReleaseYearTextView() { return this.releaseYearTextView; }
        public ImageView getTitlePosterImageView() { return this.titlePosterImageView; }
    }

    @Override
    public TitleViewHolder onCreateViewHolder(ViewGroup viewGroup, int viwType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_movie, viewGroup, false);
        return new TitleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TitleViewHolder viewHolder, final int position) {
        viewHolder.getTitleTextView().setText(this.mTitles.get(position).getTitle());
        viewHolder.getReleaseYearTextView().setText(Long.toString(this.mTitles.get(position).getReleaseYear()));
        Glide.with(this.mContext)
                .load(this.mTitles.get(position).getPosterUrl())
                .apply(new RequestOptions().override(200, 300))
                .into(viewHolder.getTitlePosterImageView());

        viewHolder.getTitleCardView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent titleIntent = new Intent(view.getContext(), TitleActivity.class);
                titleIntent.putExtra("title_id", mTitles.get(viewHolder.getAdapterPosition()).getTitleId());
                titleIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(titleIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mTitles.size();
    }
}
