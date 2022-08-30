package com.example.thedeterminedfew.ObjectSpecifications;

import android.graphics.PointF;

import com.example.thedeterminedfew.Abilities.AbilitiesEnemyGuard;
import com.example.thedeterminedfew.Abilities.Ability;
import com.example.thedeterminedfew.CharacterSpots;
import com.example.thedeterminedfew.ObjectSpec;

import java.util.ArrayList;

public class EnemySpecGuard extends ObjectSpec {
    private static final String tag = "Enemy";
    private static final String name = "guard";
    private static final float speed = 5f;
    private static final PointF relativeScale = new PointF(8f, 2f);


    //private static final CharacterSpots spot = CharacterSpots.FIRST;
    private static final int health = 60;

    private static final AbilitiesEnemyGuard enemyGuardAbilities = new AbilitiesEnemyGuard();
    private static final ArrayList<Ability> abilityList = enemyGuardAbilities.getAbilityList();
    private static final PointF frameRowAndColumn = new PointF(5,5);



    private static final String[] components = new String[] {
            "StdGraphicsComponent",
            "EnemyInputComponent",
            "EnemySpawnComponent",
            "CharacterStatusComponent",
            "CharacterAbilityComponent"
    };
    public EnemySpecGuard(CharacterSpots spot){
        super(tag, name, speed, relativeScale, components,spot,health, abilityList,frameRowAndColumn);


    }
}
