package com.example.gamersgate;

import android.appwidget.AppWidgetManager;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.ComponentName;
import android.content.Intent;
import android.support.annotation.NonNull;
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

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecAdapter.RecAdapterClickHandler {
    private EditText Gamesname;
    private TextView search;
    private Button button;
    private LinearLayoutManager layoutManager;
    private RecAdapter recAdapter;
    private RecyclerView recyclerView;
    private GameViewModel viewModel;
    public static String widget;
    private AdView mAdView;
    private TextView username;
    private Button sgin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gamesname = findViewById(R.id.GamesName);
        search = findViewById(R.id.Search);
        button = findViewById(R.id.button);
        username = findViewById(R.id.username);
        sgin = findViewById(R.id.Sign);
        recyclerView = findViewById(R.id.favrec);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recAdapter = new RecAdapter(this);
        viewModel = ViewModelProviders.of(this).get(GameViewModel.class);
        recyclerView.setVisibility(View.GONE);
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
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
            recyclerView.setVisibility(View.VISIBLE);
            search.setVisibility(View.GONE);
            button.setVisibility(View.GONE);
            Gamesname.setVisibility(View.GONE);
            recAdapter.setIsfav(true);
            viewModel.getResultsFavs().observe(this, new Observer<List<ResultsFav>>() {
                @Override
                public void onChanged(@Nullable List<ResultsFav> resultsFavs) {
                    WidgetSetup(resultsFavs);
                    recAdapter.setResultsFav(resultsFavs);
                    recyclerView.setAdapter(recAdapter);
                }
            });
        }else if(item.getItemId() == R.id.ser){
            recyclerView.setVisibility(View.GONE);
            search.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
            Gamesname.setVisibility(View.VISIBLE);

        }
        return true;
    }
    @Override
    public void onClick(Results Results) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("gamee",Results);
        Intent intent = new Intent(this,GameDetails.class);
        intent.putExtra("game",bundle);
        startActivity(intent);
    }

    private void WidgetSetup(List<ResultsFav> resultsFavs) {
        widget = null;
        for (int i = 0; i < resultsFavs.size(); i++) {
            widget += (resultsFavs.get(i).getName()+"\n");
        }
        int[] ids = AppWidgetManager.getInstance(getApplication()).getAppWidgetIds(new ComponentName(getApplication(), FavWidget.class));
        FavWidget myWidget = new FavWidget();
        myWidget.onUpdate(getApplicationContext(), AppWidgetManager.getInstance(getApplicationContext()), ids);
    }



    public void createSignInIntent(View view) {
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.GoogleBuilder().build());


        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 100) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                sgin.setVisibility(View.INVISIBLE);
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                username.setText("Hello "+user.getDisplayName());
                // ...
            } else {

            }
        }
    }

    public void signOut() {
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
    }


}
