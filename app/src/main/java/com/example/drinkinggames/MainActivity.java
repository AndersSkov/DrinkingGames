package com.example.drinkinggames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button gamesListButton, randomGameButton;
    TextView randomGameTv;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    List<ModelGames> games;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        games = new ArrayList<>();
        random = new Random();

        randomGameTv = findViewById(R.id.randomGameTv);

        gamesListButton = findViewById(R.id.gamesListButton);
        gamesListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, GamesList.class);
                startActivity(intent);
            }
        });

        randomGameButton = findViewById(R.id.randomGameButton);
        randomGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findRandomGame();
            }
        });
    }

    private void findRandomGame() {

        games.clear();
        db.collection("games").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    for(QueryDocumentSnapshot document : queryDocumentSnapshots){
                        String name  = document.getData().get("name").toString();
                        String rules = document.getData().get("rules").toString();
                        String drinkingEfficiency = document.getData().get("drinkingEfficiency").toString();
                        String requirements = document.getData().get("requirements").toString();
                        games.add(new ModelGames(drinkingEfficiency, name, requirements, rules));
                    }
                    String randomGame = games.get(random.nextInt(games.size())).getName();
                    randomGameTv.setText(randomGame);
                    randomGameTv.setVisibility(View.VISIBLE);
            }
        });
    }
}
