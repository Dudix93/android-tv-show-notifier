package com.example.android_tv_show_notifier.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_tv_show_notifier.Entities.FavouriteEntity;
import java.util.List;

@Dao
public interface FavouriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavouriteEntity... favourites);

    @Update
    void update(FavouriteEntity... favourite);

    @Delete
    void delete(FavouriteEntity favourite);

    @Query("SELECT * FROM favourites")
    List<FavouriteEntity> getAll();
}
