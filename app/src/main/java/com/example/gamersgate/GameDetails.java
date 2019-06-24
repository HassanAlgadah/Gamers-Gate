package com.example.gamersgate;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GameDetails extends AppCompatActivity {
    private TextView name;
    private TextView des;
    private TextView rating;
    private TextView reles;
    private TextView genra;
    private ImageView gamepic;
    private ImageView pc;
    private ImageView ps3;
    private ImageView ps4;
    private ImageView x1;
    private ImageView x360;
    private ImageView ns;
    private ImageView fav;
    private FavWorker favWorker;
    private Results results;
    private boolean clicked = false;
    private static String API = "https://api.rawg.io/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_details);
        Bundle bundle = getIntent().getBundleExtra("game");
        Results results = bundle.getParcelable("gamee");
        ArrayList<String> plats = bundle.getStringArrayList("plat");
        ArrayList<String> ge = bundle.getStringArrayList("ge");

        name = findViewById(R.id.name);
        des = findViewById(R.id.des);
        reles = findViewById(R.id.relsee);
        rating = findViewById(R.id.ratting);
        genra = findViewById(R.id.genra);
        gamepic = findViewById(R.id.imageView);
        pc = findViewById(R.id.pc);
        ps3 = findViewById(R.id.ps3);
        ps4 = findViewById(R.id.ps4);
        x1 = findViewById(R.id.x1);
        x360 = findViewById(R.id.x3);
        ns = findViewById(R.id.sw);
        fav = findViewById(R.id.heart);
        name.setText(results.getName());
        reles.setText(results.getreleased());

        rating.append(Double.toString(results.getRating()));
        String im = results.getbackground_image();
        Picasso.get()
                .load(im)
                .into(gamepic);
        setupplat(plats, ge);
        favWorker = new FavWorker(GameDetails.this);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlaceHolderAPI api = retrofit.create(PlaceHolderAPI.class);
        Call<game> call = api.getdetails(results.getSlug());
        call.enqueue(new Callback<game>() {
            @Override
            public void onResponse(Call<game> call, Response<game> response) {
                des.setText(response.body().getDescription_raw());
            }

            @Override
            public void onFailure(Call<game> call, Throwable t) {
                System.out.println(t);
            }
        });

    }

    private void setupplat(ArrayList<String> plats, ArrayList<String> ge) {
        for (int i = 0; i < ge.size(); i++) {
            genra.append(ge.get(i) + " ");
        }
        for (int i = 0; i < plats.size(); i++) {
            String platform = plats.get(i);
            if (platform.equals("PC")) {
                pc.setVisibility(View.VISIBLE);
            }
            if (platform.equals("Xbox 360")) {
                x360.setVisibility(View.VISIBLE);
            }
            if (platform.equals("PlayStation 3")) {
                ps3.setVisibility(View.VISIBLE);
            }
            if (platform.equals("Xbox One")) {
                x1.setVisibility(View.VISIBLE);
            }
            if (platform.equals("Nintendo Switch")) {
                ns.setVisibility(View.VISIBLE);
            }
            if (platform.equals("PlayStation 4")) {
                ps4.setVisibility(View.VISIBLE);
            }
        }
    }
    private void markAsFavorite(Results game) {
        favWorker.insertFave(game);
    }
    private void unfavoriteGame(Results results) {
        favWorker.deletFav(results);
    }


    public class StarAsyncTask extends AsyncTask<Results, Void, Results> {

        @Override
        protected Results doInBackground(Results... gamess) {
            Database database = Database.getDatabase(GameDetails.this);
            Results sgames = database.gamesDAO().getSinglegame(gamess[0].getSlug());
            return sgames;
        }

        @Override
        protected void onPostExecute(Results games) {
            super.onPostExecute(games);
            if (clicked) {
                if (games != null) {
                    unfavoriteGame(games);
                    fav.setImageResource(R.drawable.heartempty);
                } else {
                    markAsFavorite(results);
                    fav.setImageResource(R.drawable.heart);
                }
            } else {
                if (games != null) {
                    fav.setImageResource(R.drawable.heart);
                } else {
                    fav.setImageResource(R.drawable.heartempty);
                }
            }
        }
    }
}

