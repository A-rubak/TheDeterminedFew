package com.example.thedeterminedfew.Abilities;

import com.example.thedeterminedfew.R;

import java.util.ArrayList;

public class AbilitiesJewel {
    private ArrayList<Ability> abilityList = new ArrayList<>();

    public AbilitiesJewel(){
        Ability ability1 = new Ability("Enemy","Stab", 10, "Jewel uses her wand to stab the enemy\ndealing direct health damage", R.drawable.spellbook01_14);
        abilityList.add(ability1);
    }

    public ArrayList<Ability> getAbilityList(){
        return abilityList;
    }
}
