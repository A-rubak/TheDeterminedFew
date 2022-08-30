package com.example.thedeterminedfew.UI;

import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.thedeterminedfew.CurrentGameState;
import com.example.thedeterminedfew.GameState;
import com.example.thedeterminedfew.HUD.HUDLevelSelect;
import com.example.thedeterminedfew.HUD.HUDSettings;

import java.util.ArrayList;

public class UISettings {
    public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> buttons)  {

        int i = event.getActionIndex();
        int x = (int) event.getX(i);
        int y = (int) event.getY(i);
        int eventType = event.getAction() &  MotionEvent.ACTION_MASK;


        if(eventType == MotionEvent.ACTION_UP || eventType == MotionEvent.ACTION_POINTER_UP) {
            if (buttons.get(HUDSettings.BACK).contains(x, y)){
                gameState.setCurrentGameState(CurrentGameState.MAIN_MENU);
            }
            if (buttons.get(HUDSettings.MUSIC_BUTTON).contains(x, y)){
                gameState.setMusicState();
            }
        }


    }
}
