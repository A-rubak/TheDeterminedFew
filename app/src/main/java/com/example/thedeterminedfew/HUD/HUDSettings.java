package com.example.thedeterminedfew.HUD;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.thedeterminedfew.GameState;

import java.util.ArrayList;

public class HUDSettings {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;

    private static String back = "Back";

    private ArrayList<Rect> controls;
    Paint soundButtonPaint;

    public static int BACK = 0;
    public static int MUSIC_BUTTON = 1;

    HUDSettings(Point size){
        mScreenHeight = size.y;
        mScreenWidth = size.x;
        mTextFormatting = size.x / 50;
    }

    public void prepareControls(){
        int buttonWidth = mScreenWidth / 14;
        int buttonHeight = mScreenHeight / 12;
        int buttonPadding = mScreenWidth / 90;

        soundButtonPaint = new Paint();
        Rect back = new Rect( buttonPadding, buttonPadding, buttonWidth+buttonPadding,buttonPadding+buttonHeight);
        Rect checkbox = new Rect( mScreenWidth/2, mScreenHeight/2, mScreenWidth/2 + buttonHeight/2,mScreenHeight/2 + buttonHeight/2);



        controls = new ArrayList<>();
        controls.add(BACK,back);
        controls.add(MUSIC_BUTTON,checkbox);

    }
    void draw(Canvas c, Paint p, GameState gs){
        int textPadding = mScreenWidth / 80;
        // Draw the HUD
        p.setColor(Color.argb(255,255,255,255));



        p.setTextSize(mTextFormatting);
        c.drawText("Sound", mScreenWidth/2, mScreenHeight/2- textPadding, p);
        c.drawText("On/Off", mScreenWidth/2 + textPadding*2, mScreenHeight/2 + (int)(textPadding*1.5), p);
        if(gs.isMusicState()){
            soundButtonPaint.setColor(Color.argb(255,255,255,255));
        }else{
            soundButtonPaint.setColor(Color.argb(100,255,255,255));
        }
        drawText(c,p);
        drawControls(c, p);
    }

    private void drawControls(Canvas c, Paint p){
        p.setColor(Color.argb(100,255,255,255));

        Rect r = controls.get(BACK);
        c.drawRect(r.left, r.top, r.right, r.bottom, p);
        r = controls.get(MUSIC_BUTTON);
        c.drawRect(r.left, r.top, r.right, r.bottom, soundButtonPaint);

        // Set the colors back
        p.setColor(Color.argb(255,255,255,255));
    }

    private void drawText(Canvas c, Paint p) {

        p.setTextSize(mTextFormatting);

        p.setColor(Color.argb(255,255,255,255));

        Rect button = controls.get(BACK);
        float w = p.measureText(back)/2;
        c.drawText(back,button.centerX()-w,button.centerY()+w/3,p);


    }

    ArrayList<Rect> getControls(){
        return controls;
    }
}
