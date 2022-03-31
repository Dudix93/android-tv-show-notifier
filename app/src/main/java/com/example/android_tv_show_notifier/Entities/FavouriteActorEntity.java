package com.example.android_tv_show_notifier.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favouriteActors")
public class FavouriteActorEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "actor_id")
    public String actor_id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "photo_url")
    public String photo_url;

    public FavouriteActorEntity(String actor_id, String name, String photo_url) {
        this.actor_id = actor_id;
        this.name = name;
        this.photo_url = photo_url;
    }

    public String getActorId() {
        return actor_id;
    }

    public String getName() {
        return name;
    }

    public String getPhotoUrl() {
        return photo_url;
    }
}
