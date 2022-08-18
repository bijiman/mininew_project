package com.biji.mininew.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Score.class, HistoryScore.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ScoreDao scoreDao();
    public abstract HistoryScoreDao historyScoreDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(
                    context.getApplicationContext(),
                    AppDatabase.class,
                    "mininew")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}

