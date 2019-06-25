package com.example.gamersgate;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import okhttp3.internal.platform.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class GameList extends AppCompatActivity implements RecAdapter.RecAdapterClickHandler {
    private String gameName;
    private  static String API = "https://api.rawg.io/";
    private LinearLayoutManager layoutManager;
    private RecAdapter recAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);
        gameName = getIntent().getStringExtra("name");
        recyclerView = findViewById(R.id.rec);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recAdapter = new RecAdapter(this);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PlaceHolderAPI api = retrofit.create(PlaceHolderAPI.class);
        Call <game> call = api.getgames(gameName);
        call.enqueue(new Callback <game>() {
            @Override
            public void onResponse(Call <game> call, Response <game> response) {
                System.out.println(response.body().getResults().get(0).getName());
                recAdapter.setResults(response.body().getResults());
                recyclerView.setAdapter(recAdapter);
            }

            @Override
            public void onFailure(Call <game> call, Throwable t) {
            }
        });
    }

    @Override
    public void onClick(Results Results) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("gamee",Results);
        Intent intent = new Intent(this,GameDetails.class);
        intent.putExtra("game",bundle);
        startActivity(intent);
    }

}
