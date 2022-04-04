package com.example.android_tv_show_notifier.Database;

import androidx.annotation.NonNull;

import com.example.android_tv_show_notifier.Entities.FavouriteActorEntity;
import com.example.android_tv_show_notifier.Entities.FavouriteTitleEntity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FirebaseDB {

    private String userId;
    private DatabaseReference favouriteTitlesRef;
    private DatabaseReference favouriteActorsRef;
    private DatabaseReference firebaseDBRef;

    public FirebaseDB(String userId) {
        this.userId = userId;
        this.firebaseDBRef = FirebaseDatabase.getInstance().getReference();
        this.firebaseDBRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (!snapshot.hasChild(userId)) {
                    firebaseDBRef.child(userId).setValue(0);
                    firebaseDBRef.child(userId).child("favourite actors").setValue(0);
                    firebaseDBRef.child(userId).child("favourite titles").setValue(0);
                }
                favouriteTitlesRef = firebaseDBRef.child(userId).child("favourite titles").getRef();
                favouriteActorsRef = firebaseDBRef.child(userId).child("favourite actors").getRef();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void insertTitle (FavouriteTitleEntity favouriteTitleEntity) {
        this.favouriteTitlesRef.push().setValue(favouriteTitleEntity);
    }

    public void insertActor (FavouriteActorEntity favouriteActorEntity) {
        this.favouriteActorsRef.push().setValue(favouriteActorEntity);
    }

    public void deleteTitle (FavouriteTitleEntity favouriteTitleEntity) {
        
    }
}
