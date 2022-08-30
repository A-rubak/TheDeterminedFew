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

import com.example.thedeterminedfew.GameState;
import com.example.thedeterminedfew.SpellCards.Card;
import com.example.thedeterminedfew.Transform;

import java.util.ArrayList;

public class HUDMergeWindow {
    public static int MERGE_CARDS = 0;
    private Rect firstCardSlot;
    private Rect secondCardSlot;
    private Rect mergeButton;

    PointF startingPosition;
    PointF screenSize;

    Bitmap firstCardBitmap  = null;
    Bitmap secondCardBitmap = null;

    Paint cardSlotPaint = new Paint(Color.argb(255,255,255,255));

    float cardSlotWidth;
    float cardSlotHeight;
    float cardPadding;
    float buttonWidth;
    float buttonHeight;

    public HUDMergeWindow(Point sc){
        screenSize = new PointF(sc.x,sc.y);
        cardSlotWidth = screenSize.x/4;
        cardSlotHeight = (float)(screenSize.y/1.5);
        startingPosition = new PointF((screenSize.x/6),(screenSize.y/20));
        cardPadding = screenSize.x/3;

        firstCardSlot = new Rect((int)startingPosition.x,(int)startingPosition.y,(int)(screenSize.x/6+cardSlotWidth),(int)(screenSize.y/20+cardSlotHeight));
        secondCardSlot = new Rect((int)(startingPosition.x + cardPadding),(int)(screenSize.y/20),(int)(startingPosition.x+cardSlotWidth+ cardPadding),(int)(screenSize.y/20+cardSlotHeight));
        mergeButton = new Rect((int)(startingPosition.x + cardPadding*2),(int)(screenSize.y/2),(int)(startingPosition.x+cardSlotWidth/2+ cardPadding*2),(int)(screenSize.y/2+cardSlotHeight/4));

        cardSlotPaint.setColor(Color.argb(255,255,255,255));
    }

    public void draw(Canvas c, Paint p, GameState gs, Context context,ArrayList<Bitmap> currentBitmaps){
        c.drawRect(firstCardSlot, cardSlotPaint);
        c.drawRect(secondCardSlot, cardSlotPaint);

        c.drawRect(mergeButton,p);
        if(gs.isUpdateCards()){
            updateBitmaps(gs,context,currentBitmaps);
        }

        if(firstCardBitmap != null){
            c.drawBitmap(firstCardBitmap,startingPosition.x,startingPosition.y,cardSlotPaint);
        }

        if(secondCardBitmap != null){
            c.drawBitmap(secondCardBitmap,startingPosition.x + cardPadding,startingPosition.y,cardSlotPaint);
        }


    }

    private void updateBitmaps(GameState gs, Context context, ArrayList<Bitmap> currentBitmaps) {
        int i = 0;
        firstCardBitmap = null;
        secondCardBitmap = null;
        for (Card card: gs.getCardsToMerge()) {
            if(i == 0){

                Transform t = new Transform(0,cardSlotWidth,cardSlotHeight,startingPosition,screenSize);
                t.setLocation(startingPosition.x,startingPosition.y);
                card.setTransform(t);
                Bitmap icon = BitmapFactory.decodeResource(context.getResources(), card.getCardImage());
                Transform iconTransform = card.getTransform();
                firstCardBitmap = Bitmap.createScaledBitmap(icon,(int)iconTransform.getObjectWidth(),(int)iconTransform.getObjectHeight(), false);
            }else{
                PointF secondCardPosition = new PointF(startingPosition.x + cardPadding,startingPosition.y);
                Transform t = new Transform(0,cardSlotWidth,cardSlotHeight,secondCardPosition,screenSize);
                t.setLocation(secondCardPosition.x,secondCardPosition.y);
                card.setTransform(t);
                Bitmap icon = BitmapFactory.decodeResource(context.getResources(), card.getCardImage());
                Transform iconTransform = card.getTransform();
                secondCardBitmap = Bitmap.createScaledBitmap(icon,(int)iconTransform.getObjectWidth(),(int)iconTransform.getObjectHeight(), false);
            }

            i++;
        }
    }

    public Rect getMergeControls(){
        return mergeButton;
    }

}
