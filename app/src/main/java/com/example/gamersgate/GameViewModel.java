package com.example.gamersgate;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class GameViewModel extends AndroidViewModel {

    Database database;
    MutableLiveData<List <ResultsFav>> resultsFavLiveData = new MutableLiveData<>();
    LiveData<List<ResultsFav>> liveData;

    public GameViewModel(Application application) {
        super (application);
        //Get database instance:
        database = Database.getDatabase(application);
        liveData = database.gamesDAO().getAllgames();
    }

    public LiveData<List<ResultsFav>> getResultsFavs () {
            return liveData;
    }


}
