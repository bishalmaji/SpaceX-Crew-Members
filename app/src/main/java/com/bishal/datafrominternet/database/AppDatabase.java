package com.bishal.datafrominternet.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.bishal.datafrominternet.model.CrewModel;

@Database(entities = {CrewModel.class}, version  = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract CrewDao userDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {

        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "crewtable")
                    .allowMainThreadQueries()
                    .build();

        }
        return INSTANCE;
    }
}