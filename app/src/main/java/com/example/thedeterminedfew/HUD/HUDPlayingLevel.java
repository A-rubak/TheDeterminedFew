package com.example.thedeterminedfew.HUD;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

import com.example.thedeterminedfew.Abilities.Ability;
import com.example.thedeterminedfew.GameObject;
import com.example.thedeterminedfew.GameState;
import com.example.thedeterminedfew.R;
import com.example.thedeterminedfew.SpellCards.Card;
import com.example.thedeterminedfew.Transform;

import java.util.ArrayList;

public class HUDPlayingLevel {
    private int mTextFormatting;
    private int mScreenHeight;
    private int mScreenWidth;
    private Context mContext;

    private static String inspiration = "Inspiration";
    private static String mergeString = "Merge";
    private static String endTurnString = "End Turn";

    private ArrayList<ArrayList<Bitmap>> characterControls;

    private ArrayList<GameObject> playerObjects;
    private ArrayList<Rect> uiControls;
    private HUDMergeWindow mergeWindow;
    int buttonWidth;
    int buttonHeight ;
    int buttonPadding ;
    int hudXPosition ;
    int hudYPosition ;
    float cardWidth ;
    float cardHeight ;

    Bitmap cardBack;
    Bitmap cardHud;

    Rect merge;
    Rect mergeAction;
    public static int END_TURN = 0;
    public static int MERGE_ACTION = 1;
    public static int MERGE = 2;

    Paint buttonPaint;


    HUDPlayingLevel(Context c, Point size, ArrayList<GameObject> objects){
        mScreenHeight = size.y;
        mScreenWidth = size.x;
        mTextFormatting = size.x / 50;
        mContext = c;
        playerObjects = objects;

        //cardBack = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.card_back);
        //cardHud = BitmapFactory.decodeResource(mContext.getResources(),R.drawable.card_hud);

        buttonWidth = mScreenWidth / 22;
        buttonHeight = mScreenHeight / 12;
        buttonPadding = mScreenWidth / 20;
        hudXPosition = (int)(mScreenWidth/2 - (mScreenWidth/2)/2);
        hudYPosition = mScreenHeight/2 + (int)((mScreenHeight/2)/2.1);
        cardWidth = (float)(buttonWidth*2);
        cardHeight = (float)(buttonHeight*4);
        mergeWindow = new HUDMergeWindow(size);

        buttonPaint = new Paint();
        buttonPaint.setColor(Color.argb(50,255,255,255));



    }

    public void prepareControls(){

        characterControls = new ArrayList<>();
        uiControls = new ArrayList<>();

        for (GameObject object: playerObjects) {

            ArrayList<Bitmap> controls = new ArrayList<>();
            if(object.getAbilityComponent() != null){
                ArrayList<Ability> characterAbilities = object.getAbilityComponent().getAbilityList();
                PrepareCharacterHUD(controls, characterAbilities);
            }else{
                ArrayList<Card> deck = object.getCardComponent().getDeck();
                PrepareMainCharacterHUD(controls,deck);
            }

            merge =  new Rect( 0, 0, buttonWidth + buttonPadding,buttonHeight);
            Rect endTurn = new Rect( mScreenWidth - buttonWidth - buttonPadding*2, 0, mScreenWidth - buttonPadding,buttonHeight);
            uiControls.add(END_TURN,endTurn);
            uiControls.add(MERGE_ACTION,mergeWindow.getMergeControls());
            uiControls.add(MERGE,merge);
            characterControls.add(object.getCharacterSpot(),controls);

        }

    }

    private void PrepareCharacterHUD(ArrayList<Bitmap> controls, ArrayList<Ability> characterAbilities) {
        int i = 0;
        for (Ability a: characterAbilities) {

            PointF startingLocation = new PointF(hudXPosition - buttonPadding *i, hudYPosition);
            PointF screenSize = new PointF(mScreenWidth,mScreenHeight);
            Transform abilityTransform = new Transform(0, buttonWidth, buttonWidth,startingLocation,screenSize );
            abilityTransform.setLocation(startingLocation.x,startingLocation.y);

            a.setTransform(abilityTransform);
            Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(), a.getIcon());
            Transform iconTransform = a.getTransform();
            icon = Bitmap.createScaledBitmap(icon,(int)iconTransform.getObjectHeight(),(int)iconTransform.getObjectHeight(), false);
            controls.add(icon);
            i++;
        }
    }
    private void PrepareMainCharacterHUD(ArrayList<Bitmap> controls, ArrayList<Card> deck) {
        int i = 0;
        ArrayList<Card> mimicDeck = new ArrayList<>(deck);
        for (Card c: mimicDeck) {

            PointF startingLocation = new PointF(hudXPosition - buttonPadding*i, hudYPosition);
            PointF screenSize = new PointF(mScreenWidth,mScreenHeight);
            Transform abilityTransform = new Transform(0, cardWidth, cardHeight,startingLocation,screenSize );
            abilityTransform.setLocation(startingLocation.x,startingLocation.y);
            c.setTransform(abilityTransform);

            Bitmap icon = BitmapFactory.decodeResource(mContext.getResources(), c.getCardImage());
            Transform iconTransform = c.getTransform();
            icon = Bitmap.createScaledBitmap(icon,(int)iconTransform.getObjectWidth(),(int)iconTransform.getObjectHeight(), false);

            controls.add(icon);

            i++;
        }
    }
    private void UpdateMainCharacterControls() {
        for (GameObject object : playerObjects) {

            ArrayList<Bitmap> controls = new ArrayList<>();
            if (object.getAbilityComponent() == null) {
                ArrayList<Card> deck = object.getCardComponent().getDeck();
                PrepareMainCharacterHUD(controls, deck);
                characterControls.remove(object.getCharacterSpot());
                characterControls.add(object.getCharacterSpot(),controls);
            }


        }
    }
    void draw(Canvas c, Paint p, GameState gs){

        // Draw the HUD
        p.setColor(Color.argb(255,255,255,255));
        p.setTextSize(mTextFormatting);
        int textPadding = mScreenHeight/10;

        if(gs.getChosenAbility() != null){
            Ability ability = gs.getChosenAbility();
            Transform abilityTransform = ability.getTransform();
            p.setTextSize(mTextFormatting );
            c.drawText(ability.getName(),mScreenWidth/2, abilityTransform.getLocation().y ,p);
            for (String line: ability.getDescription().split("\n")) {
                c.drawText(line,mScreenWidth/2 , abilityTransform.getLocation().y + textPadding,p);
                textPadding += p.descent() - p.ascent();
            }

        }
        drawText(c,p,gs);
        drawControls(c, p,gs);

        if(gs.isMergeWindowActive()){
            for (GameObject object : playerObjects) {
                if (object.getAbilityComponent() == null) {
                    mergeWindow.draw(c,buttonPaint,gs,mContext,characterControls.get(object.getCharacterSpot()));
                }

            }
        }
        if(gs.isUpdateCards()){
            UpdateMainCharacterControls();
            gs.setUpdateCards(false);
        }
    }

    private void drawText(Canvas c, Paint p, GameState gs) {

        p.setTextSize(mTextFormatting);

        p.setColor(Color.argb(255,255,255,255));

        Rect button = uiControls.get(END_TURN);
        float w = p.measureText(endTurnString)/2;
        c.drawText(endTurnString,button.centerX()-w,button.centerY()+w/3,p);

        if(gs.getChosenAlly()!= null){
            if(gs.getChosenAlly().getCardComponent() != null){
                Log.d("Debug","Drawing the text inspiration");
                button = uiControls.get(MERGE);
                w = p.measureText(inspiration)/2;
                c.drawText(inspiration,button.centerX() - w,button.centerY(),p);
            }

        }

        if(gs.isMergeWindowActive()){
            Log.d("Debug","Drawing the text mergudes");
            button = uiControls.get(MERGE_ACTION);
            w = p.measureText(mergeString)/2;
            c.drawText(mergeString,button.centerX() - w,button.centerY(),p);
        }


    }

    private void drawControls(Canvas c, Paint p, GameState gs){

        ArrayList<GameObject> mimicObjects = new ArrayList<>(playerObjects);

        for (GameObject o : mimicObjects) {

                if (o.isHUDActive()) {
                    DrawCharacterControls(c, p, o, gs);

                }

        }
        p.setColor(Color.argb(100,255,255,255));
        Rect r = uiControls.get(END_TURN);
        c.drawRect(r, p);


        p.setColor(Color.argb(255,255,255,255));
    }

    private void DrawCharacterControls(Canvas c, Paint p, GameObject o, GameState gs) {
        Bitmap savedBitmap = null;
        PointF savedLocation = null;
        Transform abilityTransform = null;

        if(gs.getChosenAbility() != null){
            p.setColor(Color.argb(50,255,255,255));
            abilityTransform = gs.getChosenAbility().getTransform();
        }else{
            p.setColor(Color.argb(255,255,255,255));
        }

        if(o.getCardComponent() != null){

            Rect r = uiControls.get(MERGE);
            c.drawRect(r.left, r.top, r.right, r.bottom, buttonPaint);

        }

        ArrayList<Bitmap> controls = characterControls.get(o.getCharacterSpot());

        int i = 0;
        for (Bitmap button : controls) {
            PointF startingLocation = new PointF(hudXPosition - buttonPadding *i, hudYPosition);
            if(gs.getChosenAbility() != null){
                if(abilityTransform != null){
                    if(startingLocation.x == abilityTransform.getLocation().x && startingLocation.y == abilityTransform.getLocation().y){
                        savedBitmap = button;
                        savedLocation = startingLocation;

                    }
                }

            }

            c.drawBitmap(button, startingLocation.x, startingLocation.y, p);
            i++;
        }

        if(gs.getChosenAbility() != null){
            p.setColor(Color.argb(255,255,255,255));
            if(savedLocation != null && savedBitmap != null){
                c.drawBitmap(savedBitmap, savedLocation.x,savedLocation.y,p);
                DrawLine(c, p, gs, savedBitmap, savedLocation);
            }
        }
    }

    private void DrawLine(Canvas c, Paint p, GameState gs, Bitmap savedBitmap, PointF savedLocation) {
        float linePositionX = savedLocation.x + savedBitmap.getWidth()/2;
        float linePositionY = savedLocation.y + savedBitmap.getHeight()/2;
        float lineWidth = mScreenWidth/110;
        p.setColor(Color.RED);
        p.setStrokeWidth(lineWidth);
        c.drawLine(linePositionX,linePositionY, gs.getFingerPosition().x, gs.getFingerPosition().y, p);
    }

    ArrayList<Rect> getControls(){
        return uiControls;
    }

    public void turnOffAllAbilities(){
        for(GameObject o : playerObjects){
            if(o.isHUDActive()){
                o.setHUDActive(false);
            }

        }
    }
}
