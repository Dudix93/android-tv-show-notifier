package com.example.android_tv_show_notifier.Database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.android_tv_show_notifier.Entities.FavouriteActorEntity;
import com.example.android_tv_show_notifier.Entities.FavouriteTitleEntity;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirebaseDB {

    private String userId;
    private DatabaseReference favouriteTitlesRef;
    private DatabaseReference favouriteActorsRef;
    private DatabaseReference firebaseDBRef;
    private ArrayList<String> favouriteTitles;
    private ArrayList<String> favouriteActors;
    private boolean isFav;

    public FirebaseDB(String userId) {
        this.userId = userId;
        this.isFav = false;
        this.firebaseDBRef = FirebaseDatabase.getInstance().getReference();
        this.firebaseDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.hasChild(userId)) {
                    firebaseDBRef.child(userId).setValue(0);
                    firebaseDBRef.child(userId).child("favourite actors").setValue(0);
                    firebaseDBRef.child(userId).child("favourite titles").setValue(0);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void insertTitle (FavouriteTitleEntity favouriteTitleEntity) {
        favouriteTitlesRef = firebaseDBRef.child(userId).child("favourite titles").getRef();
        this.favouriteTitlesRef.push().setValue(favouriteTitleEntity);
    }

    public void insertActor (FavouriteActorEntity favouriteActorEntity) {
        favouriteActorsRef = firebaseDBRef.child(userId).child("favourite actors").getRef();
        this.favouriteActorsRef.push().setValue(favouriteActorEntity);
    }

    public void deleteTitle (FavouriteTitleEntity favouriteTitleEntity) {
        
    }

    public void getFavouriteTitles(DataCallback dataCallback) {
        this.firebaseDBRef = FirebaseDatabase.getInstance().getReference();
        favouriteTitlesRef = firebaseDBRef.child(userId).child("favourite titles").getRef();
        favouriteTitlesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataCallback.callback(snapshot);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) { }
        });
    }

    public interface DataCallback {
        void callback(DataSnapshot snapshot);
    }
}

