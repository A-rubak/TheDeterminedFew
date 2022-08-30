package com.example.thedeterminedfew.Components;

import com.example.thedeterminedfew.Abilities.Ability;

import java.util.ArrayList;

public class CharacterAbilityComponent {

    private ArrayList<Ability> abilityList = new ArrayList<>();

    public CharacterAbilityComponent(ArrayList<Ability> list){
        abilityList = list;
    }

    public ArrayList<Ability> getAbilityList() {
        return abilityList;
    }
}
