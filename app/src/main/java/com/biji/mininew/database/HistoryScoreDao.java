package com.biji.mininew.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface HistoryScoreDao {

    @Insert
    void insertHistory(HistoryScore historyScore);

    @Query("Select * from HistoryScore")
    List<HistoryScore> getHistory();
}