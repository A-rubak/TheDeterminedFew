package com.example.thedeterminedfew.ObjectSpecifications;

import android.graphics.PointF;

import com.example.thedeterminedfew.Abilities.AbilitiesJewel;
import com.example.thedeterminedfew.Abilities.Ability;
import com.example.thedeterminedfew.CharacterSpots;
import com.example.thedeterminedfew.ObjectSpec;
import com.example.thedeterminedfew.SpellCards.Card;
import com.example.thedeterminedfew.SpellCards.StartingDeck;

import java.util.ArrayList;

public class CharacterSpecMain extends ObjectSpec {
    private static final String tag = "Ally";
    private static final String name = "kvothe";
    private static final float speed = 5f;
    private static final PointF relativeScale = new PointF(8f, 2f);


    private static final int health = 100;
    private static StartingDeck mainDeck = new StartingDeck();
    private static ArrayList<Card> deck = new ArrayList<>(mainDeck.getCardList());
    private static final PointF frameRowAndColumn = new PointF(5,5);


    private static final String[] components = new String[] {
            "StdGraphicsComponent",
            "MainCharacterInputComponent",
            "PlayerSpawnComponent",
            "CharacterStatusComponent",
            "CardHolderComponent"
    };
    public CharacterSpecMain(CharacterSpots spot){

        super(tag, name, speed, relativeScale, components,spot,health, null,frameRowAndColumn,deck);
        mainDeck = new StartingDeck();
        deck = new ArrayList<>(mainDeck.getCardList());

    }
}
