package com.example.android_tv_show_notifier.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_tv_show_notifier.DownloadImageFromUrl;
import com.example.android_tv_show_notifier.R;
import com.example.android_tv_show_notifier.adapters.KnownForListAdapter;
import com.example.android_tv_show_notifier.adapters.MoviesListAdapter;
import com.example.android_tv_show_notifier.api.ImdbAPI;
import com.example.android_tv_show_notifier.api.RetrofitInstance;
import com.example.android_tv_show_notifier.models.ActorModel;
import com.example.android_tv_show_notifier.models.KnownForModel;
import com.example.android_tv_show_notifier.models.MostPopularDataDetailModel;
import com.example.android_tv_show_notifier.models.NameModel;
import com.example.android_tv_show_notifier.models.TitleModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActorActivity extends AppCompatActivity {

    private String actorId;
    private Bundle intentExtras;
    private ImageView actorPhotoImageView;
    private TextView actorSummaryTextView;
    private TextView actorNameTextView;
    private KnownForListAdapter knownForListAdapter;
    private NameModel nameModel;
    private Call<NameModel> actorAPICall;
    private ImdbAPI imdbAPI;
    private ArrayList<KnownForModel> knownForArrayList;
    private RecyclerView knownForRecyclerView;
    private Context mContext;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor);
        this.intentExtras = getIntent().getExtras();
//        if (this.intentExtras != null && this.intentExtras.containsKey("actor_id")) {
//            this.actorId = this.intentExtras.getString("actor_id");
            this.mContext = getApplicationContext();
            this.actorId = "nm0000204";
            this.actorPhotoImageView = findViewById(R.id.actor_photo);
            this.actorSummaryTextView = findViewById(R.id.actor_summary);
            this.actorNameTextView = findViewById(R.id.actor_name);
            this.knownForRecyclerView = findViewById(R.id.actor_known_for_list);
            this.imdbAPI = new RetrofitInstance().api;
            this.actorAPICall = this.imdbAPI.getActor(this.actorId);
            getActorData();
//        }
    }

    public void getActorData() {
        try {
            actorAPICall.enqueue(new Callback<NameModel>() {
                @Override
                public void onResponse(Call<NameModel> call, Response<NameModel> response) {

                    if (response.code() != 200) {
                        Toast.makeText(getApplicationContext(), response.message(), Toast.LENGTH_LONG);
                    }
                    else {
                        if (response.body() != null && response.body() instanceof NameModel) {
                            nameModel = response.body();
                            fillActorData();
                        }
                    }
                }

                @Override
                public void onFailure(Call<NameModel> call, Throwable t) {
                    Toast.makeText(mContext, t.getMessage(), Toast.LENGTH_LONG);
                }
            });
        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
        }
    }

    public void fillActorData() {
        new DownloadImageFromUrl(this.actorPhotoImageView).execute(this.nameModel.getImage());
        this.actorSummaryTextView.setText(this.nameModel.getSummary());
        this.actorNameTextView.setText(this.nameModel.getName());

        knownForArrayList = new ArrayList<KnownForModel>(nameModel.getKnownFor());
        knownForRecyclerView = (RecyclerView) findViewById(R.id.actor_known_for_list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        knownForRecyclerView.setLayoutManager(mLayoutManager);
        knownForListAdapter = new KnownForListAdapter(knownForArrayList, mContext);
        knownForRecyclerView.setAdapter(knownForListAdapter);
    }
}