package com.example.thedeterminedfew.Components;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.RectF;

import com.example.thedeterminedfew.GameObject;
import com.example.thedeterminedfew.Interfaces.GraphicsComponent;
import com.example.thedeterminedfew.ObjectSpec;
import com.example.thedeterminedfew.Transform;

public class StdGraphicsComponent implements GraphicsComponent {
    private Bitmap mBitmapIdle;
    private Bitmap mHighlightBitmapIdle;
    private Bitmap mBitmapAttack;
    private Bitmap mBitmapDamage;
    GameObject self = null;
    private int frameWidth = 510;
    private int frameHeight = 510;
    private Rect frameToDraw = new Rect(0,0,frameWidth,frameHeight);
    private Rect staticFrame = new Rect(0,0,frameWidth,frameHeight);
    private int frameCountColumn = 5;
    private int frameCountRow = 5;
    private int currentFrameColumn = 0;
    private int currentFrameRow = 0;
    private long lastFrameChangeTime = 0;
    private int frameLengthInMilliseconds = 30;

    public StdGraphicsComponent(PointF animationPoints, GameObject object) {
        frameCountColumn = (int)animationPoints.x;
        frameCountRow = (int)animationPoints.y;
        self = object;
    }

    @Override
    public void initialize(Context context, ObjectSpec spec, PointF objectSize){
        // Make a resource id out of the string of the file
        //name
        frameWidth = (int)objectSize.x*2;
        frameHeight = frameWidth;

        int idleResID = context.getResources().getIdentifier(spec.getBitmapName()+"_idle",
                        "drawable",
                        context.getPackageName());
        int attackResID = context.getResources().getIdentifier(spec.getBitmapName()+"_attack",
                "drawable",
                context.getPackageName());
        /*int damageResID = context.getResources().getIdentifier(spec.getBitmapName()+"_damage",
                "drawable",
                context.getPackageName());*/

        // Load the bitmap using the id
        mBitmapIdle = BitmapFactory.decodeResource(context.getResources(), idleResID);
        // Resize the bitmap
        mBitmapIdle = Bitmap.createScaledBitmap(mBitmapIdle,(int)frameWidth*frameCountRow,(int)frameHeight*frameCountColumn,false);
        mHighlightBitmapIdle = highlightImage(mBitmapIdle);

        mBitmapAttack = BitmapFactory.decodeResource(context.getResources(), attackResID);
        // Resize the bitmap
        mBitmapAttack = Bitmap.createScaledBitmap(mBitmapAttack,(int)frameWidth,(int)frameHeight,false);
        // Create a mirror image of the bitmap if needed
        Matrix matrix = new Matrix();
        matrix.setScale(-1, 1);


    }
    @Override
    public void draw(Canvas canvas, Paint paint, Transform t, CharacterStatusComponent statusComponent, boolean highlight) {

        int framePadding = (int)(t.getmScreenSize().x/15);
        RectF whereToDraw = new RectF(t.getLocation().x - framePadding,t.getLocation().y, t.getLocation().x + frameWidth - framePadding , t.getLocation().y + frameHeight);
        getCurrentFrame();


        if(highlight){
            canvas.drawBitmap(mHighlightBitmapIdle,frameToDraw,whereToDraw,paint);
        }else{
            canvas.drawBitmap(mBitmapIdle,frameToDraw,whereToDraw,paint);
        }

        if(statusComponent != null){
            statusComponent.drawStatus(canvas,paint);
        }

    }
    public Bitmap highlightImage(Bitmap src) {
        // create new bitmap, which will be painted and becomes result image
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth() + 96, src.getHeight() + 96, Bitmap.Config.ARGB_8888);
        // setup canvas for painting
        Canvas canvas = new Canvas(bmOut);
        // setup default color
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        // create a blur paint for capturing alpha
        Paint ptBlur = new Paint();
        ptBlur.setMaskFilter(new BlurMaskFilter(15, BlurMaskFilter.Blur.NORMAL));
        int[] offsetXY = new int[2];
        // capture alpha into a bitmap
        Bitmap bmAlpha = src.extractAlpha(ptBlur, offsetXY);
        // create a color paint
        Paint ptAlphaColor = new Paint();
        ptAlphaColor.setColor(0xFFFFFFFF);
        // paint color for captured alpha region (bitmap)
        canvas.drawBitmap(bmAlpha, offsetXY[0], offsetXY[1], ptAlphaColor);
        // free memory
        bmAlpha.recycle();

        // paint the image source
        canvas.drawBitmap(src, 0, 0, null);

        // return out final image
        return bmOut;
    }
    public void getCurrentFrame(){

        long time  = System.currentTimeMillis();

        if ( time > lastFrameChangeTime + frameLengthInMilliseconds) {
            lastFrameChangeTime = time;
            currentFrameColumn++;
            if (currentFrameColumn >= frameCountColumn) {
                currentFrameRow++;
                currentFrameColumn = 0;
            }
            if(currentFrameRow >= frameCountRow){
                currentFrameRow = 0;
            }
        }
        //update the left and right values of the source of
        //the next frame on the spritesheet
        frameToDraw.top = currentFrameRow * frameHeight;
        frameToDraw.bottom = frameToDraw.top + frameHeight;
        frameToDraw.left = currentFrameColumn * frameWidth;
        frameToDraw.right = frameToDraw.left + frameWidth;
        }


}

