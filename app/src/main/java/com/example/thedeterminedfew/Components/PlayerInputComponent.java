package com.example.thedeterminedfew.Components;

import android.graphics.PointF;
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
import com.example.thedeterminedfew.SpellCards.Card;
import com.example.thedeterminedfew.Transform;

import java.util.ArrayList;

public class PlayerInputComponent implements InputComponent, InputObserver {

    private Transform mTransform;
    private GameObject self;
    private ArrayList<Ability> abilityList = null;



    public PlayerInputComponent(GameEngine ger, GameObject object) {

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
        if(abilityList ==  null){
            abilityList = self.getAbilityComponent().getAbilityList();
        }

        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);
        int eventType = event.getAction() &  MotionEvent.ACTION_MASK;
        PointF fingerPosition = new PointF(x,y);
        gameState.setFingerPosition(fingerPosition);
        if(gameState.getCurrentGameState() == CurrentGameState.PLAYING_LEVEL){
            if(eventType == MotionEvent.ACTION_UP || eventType == MotionEvent.ACTION_POINTER_UP) {
                if(self.performedAction() || gameState.isMergeWindowActive()){
                    return;
                }
                if (mTransform.getCollider().contains(x, y)){
                    gameState.changeHud();
                    gameState.setChosenAbility(null);
                    if(!self.performedAction()){
                        gameState.setChosenAlly(self);
                        self.setHUDActive(true);
                    }

                }
                if(gameState.getChosenAbility() != null){
                    ArrayList<GameObject> mimicObject = new ArrayList<>(gameState.getEnemyList());
                    for (GameObject enemy: mimicObject) {
                        if(enemy.getTransform().getCollider().contains(x,y) && self.isHUDActive()){

                            gameState.setChosenEnemy(enemy);
                            gameState.performAction();
                        }
                    }
                    if(gameState.getChosenEnemy() == null){
                        gameState.setChosenAbility(null);
                    }
                }

            }
            else if(eventType == MotionEvent.ACTION_DOWN){

                if(self.isHUDActive()){
                    for (Ability a: abilityList) {

                        if(a.getTransform().getCollider().contains(x,y)){

                            if(gameState.getChosenAbility() == a){
                                gameState.setChosenAbility(null);
                            }else{
                                gameState.setChosenAbility(a);
                            }


                        }
                    }
                }
            }
            else if(eventType == MotionEvent.ACTION_MOVE){

                if(self.isHUDActive()){
                    ArrayList<GameObject> mimicObject = new ArrayList<>(gameState.getEnemyList());
                    for (GameObject enemy: mimicObject) {
                        if(enemy.getTransform().getCollider().contains(x,y)){

                            gameState.setChosenEnemy(enemy);
                            return;
                        }
                    }
                    gameState.setChosenEnemy(null);
                }
            }
        }





    }
}