package com.example.gamersgate;

import android.content.Context;
import android.os.AsyncTask;

public class FavWorker {
    private boolean isFav = true;
    private Database database;

    public FavWorker(Context context){
        database = Database.getDatabase(context);
    }

    public void insertFave(ResultsFav game){
        isFav = true;
        new FavAsyncTask().execute(game);
    }
    public void deletFav(ResultsFav game){
        isFav = false;
        new FavAsyncTask().execute(game);
    }

    public class FavAsyncTask extends AsyncTask<ResultsFav,Void,Void>{
        @Override
        protected Void doInBackground(ResultsFav... resultsFavs) {
            if(isFav){
                database.gamesDAO().insertgame(resultsFavs[0]);
            }else {
                database.gamesDAO().deletegame(resultsFavs[0].getSlug());
            }
            return null;
        }
    }

}
