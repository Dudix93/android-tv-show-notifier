package com.example.android_tv_show_notifier.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.android_tv_show_notifier.Entities.FavouriteActorEntity;
import com.example.android_tv_show_notifier.Entities.FavouriteTitleEntity;

@Database(entities = {FavouriteTitleEntity.class, FavouriteActorEntity.class}, version = 3, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private static final String DATABASE_NAME = "Favourites";

    public synchronized static RoomDB getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }
    public abstract FavouriteTitleDao favouriteTitleDao();

    public abstract FavouriteActorDao favouriteActorDao();
}
