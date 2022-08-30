package com.example.thedeterminedfew.HUD;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.thedeterminedfew.GameObject;
import com.example.thedeterminedfew.GameState;

import java.util.ArrayList;

public class HUDController {
    private HUD mMainMenuHUD;
    private HUDLevelSelect mLevelSelect;
    private HUDPlayingLevel mPlayingCurrentLevel;
    private HUDSettings mSettings;
    private HUDEndScreen mEndScreen;

    private Point mSize;
    public HUDController(Point size){
        mSize = size;
        mMainMenuHUD = new HUD(mSize);
        mLevelSelect = new HUDLevelSelect(mSize);
        mSettings = new HUDSettings(mSize);
        mEndScreen = new HUDEndScreen(mSize);

        mMainMenuHUD.prepareControls();
        mLevelSelect.prepareControls();
        mSettings.prepareControls();
        mEndScreen.prepareControls();
    }

    public void drawCurrentHud(Canvas c, Paint p, GameState gs){
        switch(gs.getCurrentGameState()){
            case MAIN_MENU: mMainMenuHUD.draw(c,p,gs); break;
            case LEVEL_SELECT: mLevelSelect.draw(c,p,gs); break;
            case PLAYING_LEVEL: mPlayingCurrentLevel.draw(c,p,gs); break;
            case SETTINGS: mSettings.draw(c,p,gs); break;
            case WIN:
            case LOSE:
                mEndScreen.draw(c,p,gs); break;
        }

    }

    public ArrayList<Rect> getCurrentControls(GameState gs) {
        switch (gs.getCurrentGameState()){
            case MAIN_MENU: return mMainMenuHUD.getControls();
            case LEVEL_SELECT: return mLevelSelect.getControls();
            case PLAYING_LEVEL: return mPlayingCurrentLevel.getControls();
            case SETTINGS: return mSettings.getControls();
            case WIN:
            case LOSE:
                return mEndScreen.getControls();

        }
        return null;
    }

    public void createNewLevel(Context c,ArrayList<GameObject> objects) {
        mPlayingCurrentLevel = new HUDPlayingLevel(c,mSize,objects);
        mPlayingCurrentLevel.prepareControls();
    }
    public void turnOffAbilityHud() {
        mPlayingCurrentLevel.turnOffAllAbilities();
    }

    public void resetPlayingLevel(){
        mPlayingCurrentLevel = null;
    }

}
