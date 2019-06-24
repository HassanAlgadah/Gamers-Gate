package com.example.gamersgate;

import android.content.Context;
import android.os.AsyncTask;

public class FavWorker {
    private boolean isFav = true;
    private Database database;

    public FavWorker(Context context){
        database = Database.getDatabase(context);
    }

    public void insertFave(Results game){
        isFav = true;
        new FavAsyncTask().execute(game);
    }
    public void deletFav(Results game){
        isFav = false;
        new FavAsyncTask().execute(game);
    }

    public class FavAsyncTask extends AsyncTask<Results,Void,Void>{
        @Override
        protected Void doInBackground(Results... results) {
            if(isFav){
                database.gamesDAO().insertgame(results[0]);
            }else {
                database.gamesDAO().deletegame(results[0].getSlug());
            }
            return null;
        }
    }

}
