package com.example.android_tv_show_notifier.Entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favouriteTitles")
public class FavouriteTitleEntity {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "title_id")
    public String title_id;
    public String title;
    public String poster_url;
    public int release_year;


    public FavouriteTitleEntity(String title_id, String title, int release_year, String poster_url) {
        this.title_id = title_id;
        this.title = title;
        this.release_year = release_year;
        this.poster_url = poster_url;
    }

    public String getTitleId() {
        return title_id;
    }

    public String getTitle() {
        return title;
    }

    public int getReleaseYear() {
        return release_year;
    }

    public String getPosterUrl() {
        return poster_url;
    }
}
