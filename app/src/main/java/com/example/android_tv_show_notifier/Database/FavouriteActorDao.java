package com.example.android_tv_show_notifier.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_tv_show_notifier.Entities.FavouriteActorEntity;

import java.util.List;

@Dao
public interface FavouriteActorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavouriteActorEntity... favourites);

    @Update
    void update(FavouriteActorEntity... favourite);

    @Delete
    void delete(FavouriteActorEntity favourite);

    @Query("SELECT * FROM favouriteActors")
    List<FavouriteActorEntity> getAll();
}
