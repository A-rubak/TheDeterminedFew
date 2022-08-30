package com.example.thedeterminedfew;

import android.graphics.PointF;
import android.graphics.RectF;

public class Transform {

    // These two members are for scrolling background
    private int mXClip;
    private boolean mReversedFirst = false;

    private RectF mCollider;
    private PointF mLocation;
    private boolean mFacingRight = true;
    private boolean mHeadingUp = false;
    private boolean mHeadingDown = false;
    private boolean mHeadingLeft = false;
    private boolean mHeadingRight = false;
    private float mSpeed;
    private float mObjectHeight;
    private float mObjectWidth;
    private static PointF mScreenSize;

    public Transform(float speed, float objectWidth,
                     float objectHeight,
                     PointF startingLocation,
                     PointF screenSize){

        mCollider = new RectF();
        mSpeed = speed;
        mObjectHeight = objectHeight;
        mObjectWidth = objectWidth;
        mLocation = startingLocation;
        mScreenSize = screenSize;
    }

    // Here are some helper methods that the background will use
    public boolean getReversedFirst(){
        return mReversedFirst;
    }

    public void flipReversedFirst(){
        mReversedFirst = !mReversedFirst;
    }

    public int getXClip(){
        return mXClip;
    }

    public void setXClip(int newXClip){
        mXClip = newXClip;
    }

    public PointF getmScreenSize(){
        return mScreenSize;
    }


    void headUp(){
        mHeadingUp = true;
        mHeadingDown = false;

    }

    void headDown(){
        mHeadingDown = true;
        mHeadingUp = false;
    }

    void headRight(){
        mHeadingRight = true;
        mHeadingLeft = false;
        mFacingRight = true;
    }

    void headLeft(){
        mHeadingLeft = true;
        mHeadingRight = false;
        mFacingRight = false;
    }

    public boolean headingUp(){
        return mHeadingUp;
    }

    public boolean headingDown(){
        return mHeadingDown;
    }

    public boolean headingRight(){
        return mHeadingRight;
    }

    public boolean headingLeft(){
        return mHeadingLeft;
    }

    public void updateCollider(){
        // Pull the borders in a bit (10%)
        mCollider.top = mLocation.y + (mObjectHeight / 10);
        mCollider.left = mLocation.x + (mObjectWidth /10);
        mCollider.bottom = (mCollider.top + mObjectHeight)
                - mObjectHeight/10;

        mCollider.right = (mCollider.left + mObjectWidth)
                -  mObjectWidth/10;
    }

    public float getObjectHeight(){
        return mObjectHeight;
    }

    void stopVertical(){
        mHeadingDown = false;
        mHeadingUp = false;
    }

    public float getSpeed(){
        return mSpeed;
    }

    public void setLocation(float horizontal, float vertical){
        mLocation = new PointF(horizontal, vertical);
        updateCollider();
    }


    public PointF getLocation() {

        return mLocation;
    }

    public PointF getSize(){
        return new PointF((int)mObjectWidth,
                (int)mObjectHeight);
    }

    void flip(){
        mFacingRight = !mFacingRight;
    }

    public boolean getFacingRight(){
        return mFacingRight;
    }

    public RectF getCollider(){
        return mCollider;
    }

    PointF getFiringLocation(float laserLength){
        PointF mFiringLocation = new PointF();

        if(mFacingRight) {
            mFiringLocation.x = mLocation.x
                    + (mObjectWidth / 8f);
        }else
        {
            mFiringLocation.x = mLocation.x
                    + (mObjectWidth / 8f) - (laserLength);
        }
        // Move the height down a bit of ship height from origin
        mFiringLocation.y = mLocation.y + (mObjectHeight / 1.28f);
        return mFiringLocation;
    }

    public float getObjectWidth() {
        return mObjectWidth;
    }
}
