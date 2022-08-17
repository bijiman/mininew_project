package com.biji.mininew.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.biji.mininew.Point;

@Entity
public class Score {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name ="score")
    public String score;

    public Score(String score){
        this.score = score;
    }
}
