package com.example.thedeterminedfew.UI;

import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.thedeterminedfew.Interfaces.GameEngineBroadcaster;
import com.example.thedeterminedfew.GameState;
import com.example.thedeterminedfew.Interfaces.InputObserver;

import java.util.ArrayList;

public class UIController implements InputObserver {

    private UIMainMenu mMainMenu;
    private UILevelSelect mLevelSelect;
    private UIPlayingLevel mPlayingLevel;
    private UISettings mSettings;
    private UIEndScreen mEndScreen;

    public UIController(GameEngineBroadcaster b){
        b.addObserver(this);
        mMainMenu = new UIMainMenu();
        mLevelSelect = new UILevelSelect();
        mPlayingLevel = new UIPlayingLevel();
        mSettings = new UISettings();
        mEndScreen = new UIEndScreen();
    }
        @Override
        public void handleInput(MotionEvent event, GameState gameState, ArrayList<Rect> buttons)  {


        switch (gameState.getCurrentGameState()){
            case MAIN_MENU: mMainMenu.handleInput(event,gameState,buttons); break;
            case LEVEL_SELECT:mLevelSelect.handleInput(event,gameState,buttons); break;
            case PLAYING_LEVEL:mPlayingLevel.handleInput(event,gameState,buttons); break;
            case SETTINGS:mSettings.handleInput(event,gameState,buttons); break;
            case WIN:
            case LOSE:
                mEndScreen.handleInput(event,gameState,buttons); break;
        }

    }

    public void reAddObserver(GameEngineBroadcaster b){
        b.addObserver(this);
    }
}

