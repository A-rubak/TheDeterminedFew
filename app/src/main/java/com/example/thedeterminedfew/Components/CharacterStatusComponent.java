package com.example.thedeterminedfew.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

import com.example.thedeterminedfew.HealthBar;
import com.example.thedeterminedfew.Transform;

public class CharacterStatusComponent {
    private int mStartHealth;
    private int mCurrentHealth;
    private HealthBar mHealthBar;
    private Transform mCharacterTransform;

    public CharacterStatusComponent(int health, Transform t){
        mStartHealth = health;
        mCharacterTransform = t;
        mHealthBar = new HealthBar(mCharacterTransform);
        mCurrentHealth = mStartHealth;

    }

    public void drawStatus(Canvas canvas, Paint p){
        float healthPercent = (float)mCurrentHealth /mStartHealth;

        mHealthBar.draw(canvas,p,mCharacterTransform,healthPercent);
    }


    public void changeHealthStatus(int influencePoints) {

        mCurrentHealth = mCurrentHealth - influencePoints;
        if(mCurrentHealth < 0){
            mCurrentHealth = 0;
        }

    }

    public int getHealth() {
        return mCurrentHealth;
    }
}
