package com.example.drinkinggames;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SelectedGames extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    RecyclerView recyclerViewGames;
    RecyclerView.LayoutManager gamesLayoutManager;

    List<ModelGames> games;
    GamesListAdapter gamesListAdapter;

    SearchView searchBar;
    private String globalTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_games);

        Intent intent = getIntent();
        globalTag = intent.getStringExtra("globalTag");

        games = new ArrayList<>();

        recyclerViewGames = findViewById(R.id.RecyclerView);

        gamesLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewGames.setLayoutManager(gamesLayoutManager);
        recyclerViewGames.setHasFixedSize(true);

        findAllGames();

        searchBar = findViewById(R.id.search_bar);
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if(!TextUtils.isEmpty(s.trim())){
                    searchGames(s);
                }
                else{
                    findAllGames();
                }
                return false;
            }
            @Override
            public boolean onQueryTextChange(String s) {
                if(!TextUtils.isEmpty(s.trim())){
                    searchGames(s);
                }
                else{
                    findAllGames();
                }
                return false;
            }
        });
    }


    private void searchGames(final String s) {
        if(s.isEmpty()){
            return;
        }
        games.clear();
        db.collection("games").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if( queryDocumentSnapshots.isEmpty()){
                    return;
                } else {
                    for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                        String name  = document.getData().get("name").toString();
                        String rules = document.getData().get("rules").toString();
                        String drinkingEfficiency = document.getData().get("drinkingEfficiency").toString();
                        String requirements = document.getData().get("requirements").toString();
                        String tag = document.getData().get("tag").toString();
                        if(globalTag.contains(tag) || globalTag.isEmpty()) {
                            if (name.toLowerCase().contains(s.toLowerCase())) {
                                games.add(new ModelGames(drinkingEfficiency, name, requirements, rules, tag));
                            }
                        }
                    }
                }
                gamesListAdapter = new GamesListAdapter(games, SelectedGames.this);
                recyclerViewGames.setAdapter(gamesListAdapter);
            }
        });
    }

    private void findAllGames() {
        games.clear();
        db.collection("games").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                if( queryDocumentSnapshots.isEmpty()){
                    return;
                } else {
                    for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                        String name  = document.getData().get("name").toString();
                        String rules = document.getData().get("rules").toString();
                        String drinkingEfficiency = document.getData().get("drinkingEfficiency").toString();
                        String requirements = document.getData().get("requirements").toString();
                        String tag = document.getData().get("tag").toString();
                        if(globalTag.contains(tag) || globalTag.isEmpty()) {
                            games.add(new ModelGames(drinkingEfficiency, name, requirements, rules, tag));
                        }
                    }
                }
                gamesListAdapter = new GamesListAdapter(games, SelectedGames.this);
                recyclerViewGames.setAdapter(gamesListAdapter);
            }
        });
    }
}