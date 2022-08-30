package com.example.thedeterminedfew.ObjectSpecifications;

import android.graphics.PointF;

import com.example.thedeterminedfew.Abilities.AbilitiesJewel;
import com.example.thedeterminedfew.Abilities.Ability;
import com.example.thedeterminedfew.CharacterSpots;
import com.example.thedeterminedfew.ObjectSpec;

import java.util.ArrayList;

public class CharacterSpecJewel extends ObjectSpec {
    private static final String tag = "Ally";
    private static final String name = "jewel";
    private static final float speed = 5f;
    private static final PointF relativeScale = new PointF(8f, 2f);


    private static final int health = 100;
    private static final AbilitiesJewel jewelAbilities = new AbilitiesJewel();
    private static final ArrayList<Ability> abilityList = jewelAbilities.getAbilityList();
    private static final PointF frameRowAndColumn = new PointF(5,5);


    private static final String[] components = new String[] {
            "StdGraphicsComponent",
            "PlayerInputComponent",
            "PlayerSpawnComponent",
            "CharacterStatusComponent",
            "CharacterAbilityComponent"
            };
    public CharacterSpecJewel(CharacterSpots spot){

        super(tag, name, speed, relativeScale, components,spot,health, abilityList,frameRowAndColumn);

    }
}
