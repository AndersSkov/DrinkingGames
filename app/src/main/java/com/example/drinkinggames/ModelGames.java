package com.example.drinkinggames;

import java.io.Serializable;

class ModelGames implements Serializable {
    String name, rules, requirements, drinkingEfficiency, tag;

    public ModelGames(){}

    public ModelGames(String drinkingEfficiency, String name, String requirements, String rules, String tag){
        this.name = name;
        this.drinkingEfficiency = drinkingEfficiency;
        this.requirements = requirements;
        this.rules = rules;
        this.tag = tag;
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

    public String getTag() {
        return tag;
    }
}

