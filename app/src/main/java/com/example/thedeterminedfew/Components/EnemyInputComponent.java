package com.example.thedeterminedfew.Components;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.thedeterminedfew.Abilities.Ability;
import com.example.thedeterminedfew.CurrentGameState;
import com.example.thedeterminedfew.GameEngine;
import com.example.thedeterminedfew.GameObject;
import com.example.thedeterminedfew.GameState;
import com.example.thedeterminedfew.Interfaces.InputComponent;
import com.example.thedeterminedfew.Interfaces.InputObserver;
import com.example.thedeterminedfew.Transform;

import java.util.ArrayList;

public class EnemyInputComponent implements InputComponent, InputObserver {
    private Transform mTransform;
    private GameObject self;
    private ArrayList<Ability> abilityList = null;



    public EnemyInputComponent(GameEngine ger, GameObject object) {

        ger.addObserver(this);
        self = object;


    }

    @Override
    public void setTransform(Transform transform) {
        mTransform = transform;

    }

    // Required method of InputObserver
    // interface called from the onTouchEvent method
    @Override
    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> buttons) {


        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);
        int eventType = event.getAction() &  MotionEvent.ACTION_MASK;

        if(gameState.getCurrentGameState() == CurrentGameState.PLAYING_LEVEL){
            if(eventType == MotionEvent.ACTION_UP || eventType == MotionEvent.ACTION_POINTER_UP) {
                if (mTransform.getCollider().contains(x, y)){
                    Log.d("Debug", "We do be klicking ENEMYYYY");


                }

            }
        }





    }
}
