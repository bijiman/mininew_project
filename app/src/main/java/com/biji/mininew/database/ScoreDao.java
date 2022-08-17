package com.biji.mininew.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.biji.mininew.Point;

@Dao
public interface ScoreDao {

    @Insert
    void insert(Score score);

    @Query("Select Count(score) from Score")
    int getCount();

    @Query("Delete from Score")
    void deleteAll();

}
