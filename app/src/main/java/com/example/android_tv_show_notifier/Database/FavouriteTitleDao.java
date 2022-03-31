package com.example.android_tv_show_notifier.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_tv_show_notifier.Entities.FavouriteTitleEntity;

import java.util.List;

@Dao
public interface FavouriteTitleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavouriteTitleEntity... favourites);

    @Update
    void update(FavouriteTitleEntity... favourite);

    @Delete
    void delete(FavouriteTitleEntity favourite);

    @Query("SELECT * FROM favouriteTitles")
    List<FavouriteTitleEntity> getAll();
}
