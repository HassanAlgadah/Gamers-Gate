package com.example.gamersgate;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface GamesDAO {
    @Insert
    public void insertgame(Results game);
    @Query("DELETE FROM Results WHERE slug = :id")
    void deletegame(String id);

    @Query("Select * from Results")
    public LiveData<List<Results>> getAllgames();

    @Query("SELECT * FROM Results WHERE slug = :id")
    public Results getSinglegame(String id);
}
