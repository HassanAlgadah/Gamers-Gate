package com.example.gamersgate;

import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import retrofit2.Converter;

@android.arch.persistence.room.Database(entities = ResultsFav.class, version = 1, exportSchema = false)
public abstract class Database extends RoomDatabase {
    public abstract GamesDAO gamesDAO();

    private static Database database;

    public static Database getDatabase(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context, Database.class, "FAVGame.db").fallbackToDestructiveMigration().build();
        }
        return database;
    }
}