package com.example.thedeterminedfew.UI;

import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.thedeterminedfew.CurrentGameState;
import com.example.thedeterminedfew.GameState;
import com.example.thedeterminedfew.HUD.HUDLevelSelect;

import java.util.ArrayList;

public class UILevelSelect {
    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> buttons)  {

        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);
        int eventType = event.getAction() &  MotionEvent.ACTION_MASK;


        if(eventType == MotionEvent.ACTION_UP || eventType == MotionEvent.ACTION_POINTER_UP) {
            if (buttons.get(HUDLevelSelect.BACK).contains(x, y)){
                gameState.setCurrentGameState(CurrentGameState.MAIN_MENU);
            }else if(buttons.get(HUDLevelSelect.CHAPTER_1).contains(x,y)){
                gameState.startNewGame(HUDLevelSelect.CHAPTER_1);
                gameState.setCurrentGameState(CurrentGameState.PLAYING_LEVEL);
            }else if(buttons.get(HUDLevelSelect.CHAPTER_2).contains(x,y)){
                gameState.startNewGame(HUDLevelSelect.CHAPTER_2);
                gameState.setCurrentGameState(CurrentGameState.PLAYING_LEVEL);
            }else if(buttons.get(HUDLevelSelect.CHAPTER_3).contains(x,y)){
                gameState.startNewGame(HUDLevelSelect.CHAPTER_3);
                gameState.setCurrentGameState(CurrentGameState.PLAYING_LEVEL);
            }
        }


    }
}
