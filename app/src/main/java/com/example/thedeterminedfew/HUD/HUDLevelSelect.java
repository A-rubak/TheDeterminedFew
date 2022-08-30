package com.example.thedeterminedfew.HUD;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.thedeterminedfew.GameState;

import java.util.ArrayList;

public class HUDLevelSelect {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;

    private ArrayList<Rect> controls;

    public static int BACK = 0;
    public static int CHAPTER_1 = 1;
    public static int CHAPTER_2 = 2;
    public static int CHAPTER_3 = 3;

    private static String back = "Back";
    private static String chap1 = "Chapter 1";
    private static String chap2 = "Chapter 2";
    private static String chap3 = "Chapter 3";

    HUDLevelSelect(Point size){
        mScreenHeight = size.y;
        mScreenWidth = size.x;
        mTextFormatting = size.x / 50;
    }

    public void prepareControls(){
        int buttonWidth = mScreenWidth / 14;
        int buttonHeight = mScreenHeight / 12;
        int buttonPadding = mScreenWidth / 90;

        Rect back = new Rect( buttonPadding, buttonPadding, buttonWidth+buttonPadding,buttonPadding+buttonHeight);

        Rect chapter1 = new Rect( buttonWidth + buttonPadding, mScreenHeight/2 - (mScreenHeight/2)/3 - buttonHeight-buttonPadding,
                mScreenWidth/4 + buttonWidth,
                mScreenHeight/2 + (mScreenHeight/2)/2 - buttonPadding);

        Rect chapter2 = new Rect(chapter1.right + buttonPadding, mScreenHeight/2 - (mScreenHeight/2)/3 - buttonHeight-buttonPadding,
                chapter1.right + mScreenWidth/4,
                mScreenHeight/2 + (mScreenHeight/2)/2 - buttonPadding);

        Rect chapter3 = new Rect(chapter2.right + buttonPadding, mScreenHeight/2 - (mScreenHeight/2)/3 - buttonHeight-buttonPadding,
                chapter2.right + mScreenWidth/4,
                mScreenHeight/2 + (mScreenHeight/2)/2 - buttonPadding);

        controls = new ArrayList<>();
        controls.add(BACK,back);
        controls.add(CHAPTER_1, chapter1);
        controls.add(CHAPTER_2, chapter2);
        controls.add(CHAPTER_3, chapter3);
    }
    void draw(Canvas c, Paint p, GameState gs){

        // Draw the HUD
        p.setColor(Color.argb(255,255,255,255));
        p.setTextSize(mTextFormatting);


        if(gs.getGameOver()){
            p.setTextSize(mTextFormatting * 3);
            c.drawText("Select a Level",
                    mScreenWidth /3, mScreenHeight /2 - mScreenHeight /3 ,p);
        }

        drawText(c,p);
        drawControls(c, p);
    }

    private void drawText(Canvas c, Paint p) {

        p.setTextSize(mTextFormatting);

        p.setColor(Color.argb(255,255,255,255));

        Rect button = controls.get(BACK);
        float w = p.measureText(back)/2;
        c.drawText(back,button.centerX()-w,button.centerY()+w/3,p);

        button = controls.get(CHAPTER_1);
        w = p.measureText(chap1)/2;
        c.drawText(chap1,button.centerX() - w,button.centerY(),p);

        button = controls.get(CHAPTER_2);
        w = p.measureText(chap2)/2;
        c.drawText(chap2,button.centerX() - w,button.centerY(),p);

        button = controls.get(CHAPTER_3);
        w = p.measureText(chap3)/2;
        c.drawText(chap3,button.centerX() - w,button.centerY(),p);
        p.setColor(Color.argb(255,255,255,255));
    }

    private void drawControls(Canvas c, Paint p){
        p.setColor(Color.argb(100,255,255,255));

        for(Rect r : controls){
            c.drawRect(r.left, r.top, r.right, r.bottom, p);
        }

        // Set the colors back
        p.setColor(Color.argb(255,255,255,255));
    }

    ArrayList<Rect> getControls(){
        return controls;
    }
}
