package com.example.gamersgate;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface GamesDAO {
    @Insert
    public void insertgame(ResultsFav game);
    @Query("DELETE FROM ResultsFav WHERE slug = :id")
    void deletegame(String id);

    @Query("Select * from ResultsFav")
    public LiveData<List<ResultsFav>> getAllgames();

    @Query("SELECT * FROM ResultsFav WHERE slug = :id")
    public ResultsFav getSinglegame(String id);
}
