package com.example.thedeterminedfew.UI;

import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.thedeterminedfew.CurrentGameState;
import com.example.thedeterminedfew.GameState;
import com.example.thedeterminedfew.HUD.HUD;

import java.util.ArrayList;

public class UIMainMenu {

    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> buttons)  {

        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);
        int eventType = event.getAction() &  MotionEvent.ACTION_MASK;


        if(eventType == MotionEvent.ACTION_UP || eventType == MotionEvent.ACTION_POINTER_UP) {
            if (buttons.get(HUD.PLAY).contains(x, y)){
                gameState.setCurrentGameState(CurrentGameState.LEVEL_SELECT);
            }
            if (buttons.get(HUD.SETTINGS).contains(x, y)){
                gameState.setCurrentGameState(CurrentGameState.SETTINGS);
            }
        }


    }


}
