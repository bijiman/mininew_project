package com.biji.mininew.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class HistoryScore {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name ="history_score")
    public int history_score;

    public HistoryScore(int history_score){
        this.history_score = history_score;
    }
}