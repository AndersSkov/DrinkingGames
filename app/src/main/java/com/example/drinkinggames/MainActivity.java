package com.example.drinkinggames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button selectedGamesButton, randomGameButton, allGamesButton;
    TextView randomGameTv;

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    List<ModelGames> games;
    Random random;

    String globalTag;

    CheckBox checkSpillekort, checkTerninger, checkFodbold, checkKopper, checkBong, checkBordtennisbold, checkUNO, checkFrisbee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        globalTag = "";
        games = new ArrayList<>();
        random = new Random();

        randomGameTv = findViewById(R.id.randomGameTv);

        selectedGamesButton = findViewById(R.id.showSelectedGamesButton);
        selectedGamesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SelectedGames.class);
                intent.putExtra("globalTag", globalTag);
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

        checkSpillekort = findViewById(R.id.checkSpillekort);
        checkTerninger = findViewById(R.id.checkTerninger);
        checkFodbold = findViewById(R.id.checkFodbold);
        checkKopper = findViewById(R.id.checkKopper);
        checkBong = findViewById(R.id.checkBong);
        checkBordtennisbold = findViewById(R.id.checkBordtennisbold);
        checkUNO = findViewById(R.id.checkUNO);
        checkFrisbee = findViewById(R.id.checkFrisbee);
    }

    public void updateTags(View view) {
        switch (view.getId()){
            case R.id.checkBong:
                if(checkBong.isChecked()){
                    globalTag = globalTag+"bong";
                } else {
                    globalTag = globalTag.replace("bong", "");
                }
                break;
            case R.id.checkSpillekort:
                if(checkSpillekort.isChecked()){
                    globalTag = globalTag+"spillekort";
                } else {
                    globalTag = globalTag.replace("spillekort","");
                }
                break;
            case R.id.checkFodbold:
                if(checkFodbold.isChecked()){
                    globalTag = globalTag+"bold";
                } else {
                    globalTag = globalTag.replace("bold", "");
                }
                break;
            case R.id.checkBordtennisbold:
                if(checkBordtennisbold.isChecked()){
                    globalTag = globalTag+"bordtennis";
                } else {
                    globalTag = globalTag.replace("bordtennis", "");
                }
                break;
            case R.id.checkTerninger:
                if(checkTerninger.isChecked()){
                    globalTag = globalTag+"terninger";
                } else {
                    globalTag = globalTag.replace("terninger", "");
                }
                break;
            case R.id.checkKopper:
                if (checkKopper.isChecked()) {
                    globalTag = globalTag+"kopper";
                } else {
                    globalTag = globalTag.replace("kopper" , "");
                }
                break;
            case R.id.checkUNO:
                if(checkUNO.isChecked()){
                    globalTag = globalTag+"uno";
                } else {
                    globalTag = globalTag.replace("uno", "");
                }
                break;
            case R.id.checkFrisbee:
                if(checkFrisbee.isChecked()){
                    globalTag = globalTag+"frisbee";
                } else {
                    globalTag = globalTag.replace("frisbee", "");
                }
                break;
        }
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
                        String tag = document.getData().get("tag").toString();
                        if(globalTag.contains(tag) || globalTag.isEmpty()) {
                            games.add(new ModelGames(drinkingEfficiency, name, requirements, rules, tag));
                        }
                    }
                    if(games.size() == 0){
                        randomGameTv.setText("Intet tilgængeligt spil");
                        randomGameTv.setVisibility(View.VISIBLE);
                    } else {
                        String randomGame = games.get(random.nextInt(games.size())).getName();
                        randomGameTv.setText(randomGame);
                        randomGameTv.setVisibility(View.VISIBLE);
                    }
            }
        });
    }
}
