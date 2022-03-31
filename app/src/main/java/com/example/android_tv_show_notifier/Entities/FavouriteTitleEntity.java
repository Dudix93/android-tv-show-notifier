package com.example.android_tv_show_notifier.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favouriteTitles")
public class FavouriteTitleEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "favouriteId")
    public String favouriteId;

    public FavouriteTitleEntity(String favouriteId) {
        this.favouriteId = favouriteId;
    }
}