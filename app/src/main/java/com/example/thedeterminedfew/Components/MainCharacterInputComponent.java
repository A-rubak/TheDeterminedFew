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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

public class MainCharacterInputComponent implements InputObserver, InputComponent {

    private Transform mTransform;
    private GameObject self;
    private ArrayList<Card>  currentDeck = null;
    private Card chosenCard = null;


    public MainCharacterInputComponent(GameEngine ger, GameObject object) {

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
        if(self.getAbilityComponent() != null){
            return;
        }

        currentDeck = self.getCardComponent().getDeck();


        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);
        int eventType = event.getAction() &  MotionEvent.ACTION_MASK;
        PointF fingerPosition = new PointF(x,y);
        gameState.setFingerPosition(fingerPosition);
        if(gameState.getCurrentGameState() == CurrentGameState.PLAYING_LEVEL){
            if(eventType == MotionEvent.ACTION_UP ) {
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
                    if(self.isHUDActive()){
                        ArrayList<GameObject> mimicObject = new ArrayList<>(gameState.getEnemyList());
                        for (GameObject enemy: mimicObject) {
                            if(enemy.getTransform().getCollider().contains(x,y) && self.isHUDActive()){
                                gameState.setChosenEnemy(enemy);
                                gameState.performAction();
                                currentDeck.remove(chosenCard);
                            }
                        }
                        if(gameState.getChosenEnemy() == null){
                            gameState.setChosenAbility(null);
                        }
                    }


                }


            }
            else if(eventType == MotionEvent.ACTION_DOWN){
                Log.d("Debug", "We do be klicking DOWN");
                if(self.isHUDActive() && !gameState.isMergeWindowActive()){
                    ArrayList<Card> mimicDeck = new ArrayList<>(currentDeck);
                    for (Card c: mimicDeck) {

                        if(c == null){
                            return;
                        }

                        if(c.getTransform().getCollider().contains(x,y)){
                            chosenCard = c;
                            Log.d("Debug", "We do be klicking CARD YAAAY");
                            Ability a = c.transformIntoAbility();
                            if(gameState.getChosenAbility() == a){
                                gameState.setChosenAbility(null);
                            }else{
                                gameState.setChosenAbility(a);
                            }


                        }
                    }
                }

                if(gameState.isMergeWindowActive()){
                    ArrayList<Card> mimicDeck = new ArrayList<>(currentDeck);
                    Collections.reverse(mimicDeck);
                    for (Card c: mimicDeck) {


                        if(c.getTransform().getCollider().contains(x,y) && gameState.getCardsToMerge().size() < gameState.MAX_MERGE_SIZE){
                            Log.d("Debug", "We do be klicking CARD YAAAY");
                            currentDeck.remove(c);
                            gameState.addCardToMerge(c);
                            return;

                        }
                    }
                    mimicDeck.clear();
                    mimicDeck.addAll(gameState.getCardsToMerge());
                    for (Card c: mimicDeck) {
                        if(c.getTransform().getCollider().contains(x,y)){
                            Log.d("Debug", "We do be klicking CARD YAAAY");
                            currentDeck.add(c);
                            gameState.removeCardToMerge(c);
                            return;

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
