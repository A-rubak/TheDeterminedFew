package com.example.thedeterminedfew;

import android.graphics.PointF;

import com.example.thedeterminedfew.Abilities.Ability;
import com.example.thedeterminedfew.SpellCards.Card;

import java.util.ArrayList;

public abstract class ObjectSpec {

    private String mTag;
    private String mName;
    private float mSpeed;
    private PointF mSizeScale;
    private String[] mComponents;

    private int mHealth;
    private CharacterSpots objectSpot;
    private ArrayList<Ability> abilityList;
    private ArrayList<Card> deck;

    private PointF animationPoints;

    public ObjectSpec(String tag, String bitmapName,
               float speed, PointF relativeScale,
               String[] components) {

        mTag = tag;
        mName = bitmapName;
        mSpeed = speed;
        mSizeScale = relativeScale;
        mComponents = components;
    }

    public ObjectSpec(String tag, String bitmapName,
                      float speed, PointF relativeScale,
                      String[] components, CharacterSpots spot, int health, ArrayList<Ability> list, PointF frameRowAndColumn) {

        mTag = tag;
        mName = bitmapName;
        mSpeed = speed;
        mSizeScale = relativeScale;
        mComponents = components;
        objectSpot = spot;
        mHealth = health;
        abilityList = list;
        animationPoints = frameRowAndColumn;
    }

    public ObjectSpec(String tag, String bitmapName,
                      float speed, PointF relativeScale,
                      String[] components, CharacterSpots spot, int health, ArrayList<Ability> list, PointF frameRowAndColumn, ArrayList<Card> cardList) {

        mTag = tag;
        mName = bitmapName;
        mSpeed = speed;
        mSizeScale = relativeScale;
        mComponents = components;
        objectSpot = spot;
        mHealth = health;
        deck = cardList;
        animationPoints = frameRowAndColumn;
    }
    String getTag() {
        return mTag;
    }
    public String getBitmapName() {
        return mName;
    }
    float getSpeed() {
        return mSpeed;
    }
    PointF getScale() {
        return mSizeScale;
    }
    String[] getComponents() {
        return mComponents;
    }
    CharacterSpots getObjectSpot(){return objectSpot;}
    int getHealth(){return mHealth;}
    ArrayList<Ability> getAbilityList(){return abilityList;};

    public PointF getAnimationPoints() {
        return animationPoints;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }
}