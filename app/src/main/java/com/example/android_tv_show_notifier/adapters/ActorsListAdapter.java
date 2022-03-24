package com.example.android_tv_show_notifier.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.android_tv_show_notifier.DownloadImageFromUrl;
import com.example.android_tv_show_notifier.activities.ActorActivity;
import com.example.android_tv_show_notifier.activities.TitleActivity;
import com.example.android_tv_show_notifier.models.ActorModel;
import com.example.android_tv_show_notifier.models.ActorModel;
import com.example.android_tv_show_notifier.R;

import java.util.ArrayList;

public class ActorsListAdapter extends RecyclerView.Adapter<ActorsListAdapter.ActorViewHolder> {

    private ArrayList<ActorModel> mActors = new ArrayList<ActorModel>();
    private Context mContext;

    public ActorsListAdapter(ArrayList<ActorModel> actors, Context context) {
        this.mActors = actors;
        this.mContext = context;
    }

    public static class ActorViewHolder extends RecyclerView.ViewHolder {
        TextView actorNameTextView;
        ImageView actorPhotoImageView;

        public ActorViewHolder(View view) {
            super(view);
            this.actorNameTextView = view.findViewById(R.id.actor_name);
            this.actorPhotoImageView = view.findViewById(R.id.actor_photo);
        }
        
        public TextView getActorNameTextView() { return this.actorNameTextView; }
        public ImageView getActorPhotoImageView() { return this.actorPhotoImageView; }
    }

    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup viewGroup, int viwType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_actor, viewGroup, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActorViewHolder viewHolder, final int position) {
        viewHolder.getActorNameTextView().setText(this.mActors.get(position).getName());
        new DownloadImageFromUrl(viewHolder.getActorPhotoImageView()).execute(this.mActors.get(position).getImage());
        Glide.with(this.mContext)
                .load(this.mActors.get(position).getImage())
                .apply(new RequestOptions().override(200, 300))
                .into(viewHolder.getActorPhotoImageView());

        viewHolder.getActorPhotoImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent actorIntent = new Intent(view.getContext(), ActorActivity.class);
                actorIntent.putExtra("actor_id", mActors.get(viewHolder.getAdapterPosition()).getId());
                actorIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(actorIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mActors.size();
    }
}
