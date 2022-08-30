package com.example.thedeterminedfew.UI;

import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

import com.example.thedeterminedfew.GameState;
import com.example.thedeterminedfew.HUD.HUDPlayingLevel;

import java.util.ArrayList;

public class UIPlayingLevel {
    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> buttons)  {

        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);
        int eventType = event.getAction() &  MotionEvent.ACTION_MASK;


        if(eventType == MotionEvent.ACTION_UP || eventType == MotionEvent.ACTION_POINTER_UP) {

            if(buttons.get(HUDPlayingLevel.END_TURN).contains(x,y) && !gameState.isMergeWindowActive()){
                Log.d("Debug", "We do be klicking end turn");
                gameState.enemyAction();
                gameState.drawCards();
                gameState.setUpdateCards(true);

            }
            if(buttons.get(HUDPlayingLevel.MERGE).contains(x,y) && gameState.getChosenAlly() != null){
                if(gameState.getChosenAlly().getCardComponent() != null){
                    Log.d("Debug", "We do be klicking merge");
                    gameState.emptyMergeList();

                    gameState.setUpdateCards(true);
                    gameState.changeMergeActiveStatus();
                }


            }
            if(buttons.get(HUDPlayingLevel.MERGE_ACTION).contains(x,y) && gameState.isMergeWindowActive()){
                Log.d("Debug", "We do be klicking MERGERINIS");
                if(gameState.getCardsToMerge().isEmpty()){
                    return;
                }else if(gameState.getCardsToMerge().size() == gameState.MAX_MERGE_SIZE){
                    gameState.mergeCards();
                }



            }
        }


    }
}
