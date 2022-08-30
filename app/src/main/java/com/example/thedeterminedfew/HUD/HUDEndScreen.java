package com.example.thedeterminedfew.HUD;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

import com.example.thedeterminedfew.CurrentGameState;
import com.example.thedeterminedfew.GameState;

import java.util.ArrayList;

public class HUDEndScreen {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;

    private ArrayList<Rect> controls;

    public static int BACK = 0;
    private static String back = "Back";

    HUDEndScreen(Point size){
        mScreenHeight = size.y;
        mScreenWidth = size.x;
        mTextFormatting = size.x / 50;
    }

    public void prepareControls(){
        int buttonWidth = mScreenWidth / 14;
        int buttonHeight = mScreenHeight / 12;
        int buttonPadding = mScreenWidth / 90;

        Rect back = new Rect( buttonPadding, buttonPadding, buttonWidth+buttonPadding,buttonPadding+buttonHeight);



        controls = new ArrayList<>();
        controls.add(BACK,back);

    }
    void draw(Canvas c, Paint p, GameState gs){
        int textPadding = mScreenWidth / 50;
        // Draw the HUD
        p.setColor(Color.argb(255,255,255,255));
        p.setTextSize(mTextFormatting);


        int survivedAllies = (gs.getAllyList().size());
        if(gs.getCurrentGameState() == CurrentGameState.WIN){
            drawWinScreen(c, p, textPadding,  survivedAllies);


        }else{
            drawLoseScreen(c, p, textPadding);
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


    }
    private void drawLoseScreen(Canvas c, Paint p, int textPadding) {
        float textLength = p.measureText("Defeat!");
        c.drawText("Defeat!", mScreenWidth/2 - textLength/2, mScreenHeight/4, p);
        textLength = p.measureText("The enemy overpowered you, try again!");
        c.drawText("The enemy overpowered you, try again!", mScreenWidth/2 - textLength/2 , mScreenHeight/3, p);
    }

    private void drawWinScreen(Canvas c, Paint p, int textPadding, int surrvivedAllies) {
        int totalScore = 0;
        float textLength = p.measureText("Enemy Defeated!");
        c.drawText("Enemy Defeated!", mScreenWidth/2 - textLength/2, mScreenHeight/4, p);
        textLength = p.measureText("Score");
        c.drawText("Score", mScreenWidth/2 - textLength/2, mScreenHeight/3 + textPadding, p);
        textLength = p.measureText("Level completed: 100");
        c.drawText("Level completed: ", mScreenWidth/2 - textLength, mScreenHeight/3+ textPadding *2, p);
        c.drawText("100", mScreenWidth/2 + textLength, mScreenHeight/3+ textPadding *2, p);

        totalScore +=100;

        c.drawText("Heroes alive: " , mScreenWidth/2 - textLength, mScreenHeight/3+ textPadding *3, p);
        c.drawText(surrvivedAllies + " * 50" , mScreenWidth/2 + textLength, mScreenHeight/3+ textPadding *3, p);
        totalScore += surrvivedAllies *50;

        c.drawText("Total Score: ", mScreenWidth/2 - textLength, mScreenHeight/3+ textPadding *4, p);
        c.drawText("" + totalScore, mScreenWidth/2 + textLength, mScreenHeight/3+ textPadding *4, p);
    }

    private void drawControls(Canvas c, Paint p){
        p.setColor(Color.argb(100,255,255,255));

        Rect r = controls.get(BACK);
        c.drawRect(r.left, r.top, r.right, r.bottom, p);

        // Set the colors back
        p.setColor(Color.argb(255,255,255,255));
    }

    ArrayList<Rect> getControls(){
        return controls;
    }
}
