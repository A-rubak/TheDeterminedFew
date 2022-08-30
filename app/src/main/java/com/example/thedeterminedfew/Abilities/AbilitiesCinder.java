package com.example.thedeterminedfew.Abilities;

import com.example.thedeterminedfew.R;

import java.util.ArrayList;

public class AbilitiesCinder {
    private ArrayList<Ability> abilityList = new ArrayList<>();

    public AbilitiesCinder(){
        Ability ability1 = new Ability("Enemy","Wind Blast", 10, "Cinder channels her magic and staggers enemies \nwith a powerful blast of wind", R.drawable.spellbook01_79);
        abilityList.add(ability1);

    }

    public ArrayList<Ability> getAbilityList(){
        return abilityList;
    }
}

