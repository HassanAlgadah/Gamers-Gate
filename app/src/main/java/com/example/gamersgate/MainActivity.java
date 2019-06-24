package com.example.gamersgate;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements RecAdapter.RecAdapterClickHandler {
    private EditText Gamesname;
    private TextView search;
    private Button button;
    private LinearLayoutManager layoutManager;
    private RecAdapter recAdapter;
    private RecyclerView recyclerView;
    private GameViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gamesname = findViewById(R.id.GamesName);
        search = findViewById(R.id.Search);
        button = findViewById(R.id.button);
        recyclerView = findViewById(R.id.favrec);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recAdapter = new RecAdapter(this);
        viewModel = ViewModelProviders.of(this).get(GameViewModel.class);


    }

    public void search(View v) {

        Intent intent = new Intent(this, GameList.class);
        intent.putExtra("name", Gamesname.getText().toString());
        startActivity(intent);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        getSupportActionBar().setTitle("Gamers Gate");
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.fav) {
            search.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            Gamesname.setVisibility(View.GONE);
            recAdapter.setIsfav(true);
            viewModel.getResultsFavs().observe(this, new Observer<List<ResultsFav>>() {
                @Override
                public void onChanged(@Nullable List<ResultsFav> resultsFavs) {
                    recAdapter.setResultsFav(resultsFavs);
                    recyclerView.setAdapter(recAdapter);
                    System.out.println(resultsFavs.get(0).getName());
                }
            });

        }
        return true;
    }

    @Override
    public void onClick(Results Results) {

    }
}
