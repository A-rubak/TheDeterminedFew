package com.example.thedeterminedfew.Abilities;

import android.graphics.Bitmap;

import com.example.thedeterminedfew.Transform;

public class Ability {
    private String mTag;
    private String mName;
    private int mInfluencePoints;
    private String mDescription;
    private int mAbilityIcon;
    private Transform mTransform;

    public Ability(String tag, String name, int influence, String description, int icon){
        mTag = tag;
        mInfluencePoints = influence;
        mDescription = description;
        mAbilityIcon = icon;
        mName = name;

    }

    public int getIcon(){
        return mAbilityIcon;
    }

    public int getInfluencePoints(){
        return mInfluencePoints;
    }

    public String getTag(){
        return mTag;
    }
    public String getDescription(){
        return mDescription;
    }

    public String getName() {
        return mName;
    }

    public Transform getTransform() {
        return mTransform;
    }

    public void setTransform(Transform t) {
        mTransform = t;
    }
}
