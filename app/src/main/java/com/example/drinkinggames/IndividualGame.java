package com.example.drinkinggames;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class IndividualGame extends AppCompatActivity {

    ModelGames game;

    TextView nameOfGameTv, rulesTv, requirementsTv, drinkingEfficiencyTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_game);

        final Intent intent = getIntent();
        game = (ModelGames) intent.getSerializableExtra("ModelGame");

        nameOfGameTv = findViewById(R.id.nameOfGameIndividual);
        rulesTv = findViewById(R.id.reglerOverride);
        requirementsTv = findViewById(R.id.requirementsOverride);
        drinkingEfficiencyTv = findViewById(R.id.drinkingEffOverride);

        // Enable scroll
        rulesTv.setMovementMethod(new ScrollingMovementMethod());

        // Skips to next line
        String rule = game.getRules().replace("bbb", "\n");

        nameOfGameTv.setText(game.getName());
        rulesTv.setText(rule);
        requirementsTv.setText(game.getRequirements());
        drinkingEfficiencyTv.setText(game.getDrinkingEfficiency());
    }
}
