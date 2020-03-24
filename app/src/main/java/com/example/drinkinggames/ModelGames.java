package com.example.drinkinggames;

import java.io.Serializable;

class ModelGames implements Serializable {
    String name, rules, requirements, drinkingEfficiency;

    public ModelGames(){}

    public ModelGames(String drinkingEfficiency, String name, String requirements, String rules){
        this.name = name;
        this.drinkingEfficiency = drinkingEfficiency;
        this.requirements = requirements;
        this.rules = rules;
    }

    public String getName() {
        return name;
    }

    public String getRules() {
        return rules;
    }

    public String getRequirements() {
        return requirements;
    }

    public String getDrinkingEfficiency() {
        return drinkingEfficiency;
    }
}

