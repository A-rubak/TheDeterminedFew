package com.example.thedeterminedfew.HUD;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.thedeterminedfew.GameState;

import java.util.ArrayList;

public class HUD {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;

    private ArrayList<Rect> controls;

    public static int PLAY = 0;
    public static int SETTINGS = 1;

    private static String play = "Play";
    private static String settings = "Settings";

    HUD(Point size){
        mScreenHeight = size.y;
        mScreenWidth = size.x;
        mTextFormatting = size.x / 50;

    }

    public void prepareControls(){
        int buttonWidth = mScreenWidth / 14;
        int buttonHeight = mScreenHeight / 12;
        int buttonPadding = mScreenWidth / 90;

        Rect play = new Rect(mScreenWidth/2 - buttonWidth, mScreenHeight/2 + (mScreenHeight/2)/2-buttonHeight-buttonPadding,
                mScreenWidth/2 + buttonPadding,
                mScreenHeight/2 + (mScreenHeight/2)/2 -buttonPadding);

        Rect settings = new Rect(mScreenWidth - buttonWidth - buttonPadding, buttonPadding,
                mScreenWidth - buttonPadding,
                buttonPadding+buttonHeight);

        controls = new ArrayList<>();
        controls.add(PLAY, play);
        controls.add(SETTINGS, settings);
    }

    void draw(Canvas c, Paint p, GameState gs){

        // Draw the HUD
        p.setColor(Color.argb(255,255,255,255));
        p.setTextSize(mTextFormatting);


        if(gs.getGameOver()){
            p.setTextSize(mTextFormatting * 5);
            c.drawText("PRESS PLAY",
                    mScreenWidth /4, mScreenHeight /2 ,p);
        }

        if(gs.getPaused() && !gs.getGameOver()){
            p.setTextSize(mTextFormatting * 5);
            c.drawText("PAUSED",
                    mScreenWidth /3, mScreenHeight /2 ,p);
        }

        drawControls(c, p);
        drawText(c,p);
    }

    private void drawText(Canvas c, Paint p) {

        p.setTextSize(mTextFormatting);

        p.setColor(Color.argb(255,255,255,255));

        Rect button = controls.get(PLAY);
        float w = p.measureText(play)/2;
        c.drawText("Play",button.centerX()-w,button.centerY(),p);

        button = controls.get(SETTINGS);
        w = p.measureText(settings)/2;
        c.drawText("Settings",button.centerX() - w,button.centerY(),p);
        p.setColor(Color.argb(255,255,255,255));
    }

    private void drawControls(Canvas c, Paint p){
        p.setColor(Color.argb(100,255,255,255));
        p.setTextSize(mTextFormatting);
        float w = p.measureText(settings)/2;
        float textSize = p.getTextSize();
        for(Rect r : controls){
            c.drawRect(r.left - w, r.top - textSize, r.right + w, r.bottom, p);
        }

        // Set the colors back
        p.setColor(Color.argb(255,255,255,255));
    }


    ArrayList<Rect> getControls(){
        return controls;
    }


}