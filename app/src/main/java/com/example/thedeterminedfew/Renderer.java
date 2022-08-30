package com.example.thedeterminedfew;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.thedeterminedfew.HUD.HUDController;
import com.example.thedeterminedfew.Levels.LevelChapterOne;

import java.util.ArrayList;

class Renderer{
    private Canvas mCanvas;
    private SurfaceHolder mSurfaceHolder;
    private Paint mPaint;
    private GameObject storedGameObject = null;

    Renderer(SurfaceView sh){
        mSurfaceHolder = sh.getHolder();
        mPaint = new Paint();
    }

    void draw(ArrayList<GameObject> objects, GameState gs, HUDController hud, ParticleSystem ps) {
        if (mSurfaceHolder.getSurface().isValid()) {
            mCanvas = mSurfaceHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255, 0, 0, 0));

            if (gs.getDrawing()) {
                for (GameObject object : objects) {
                    if(object.checkActive()) {
                        if(object.isHUDActive()){
                            if(gs.isMergeWindowActive()){
                                mPaint.setColor(Color.argb(50,255,255,255));
                                object.draw(mCanvas, mPaint,false);
                            }else{
                                mPaint.setColor(Color.argb(255,255,255,255));
                                object.draw(mCanvas, mPaint,true);
                            }
                        }

                        if((gs.getChosenAbility() != null && object.getTag().equals("Ally")) || (gs.isMergeWindowActive())){
                            mPaint.setColor(Color.argb(50,255,255,255));
                            object.draw(mCanvas, mPaint,false);
                        }else if(gs.getChosenAbility() != null && gs.getChosenEnemy() != null){
                            mPaint.setColor(Color.argb(255,255,255,255));
                            object.draw(mCanvas, mPaint, object == gs.getChosenEnemy());

                        }
                        else{
                            mPaint.setColor(Color.argb(255,255,255,255));
                            object.draw(mCanvas, mPaint,false);
                        }



                    }
                }
            }

            if(gs.getGameOver()) {
                // Draw a background graphic here
                objects.get(LevelChapterOne.BACKGROUND_INDEX).draw(mCanvas, mPaint,false);
            }
            if(ps.mIsRunning){
                ps.draw(mCanvas, mPaint);
            }

            // Draw a particle system explosion here

            // Now we draw the HUD on top of everything else
            hud.drawCurrentHud(mCanvas, mPaint, gs);

            mSurfaceHolder.unlockCanvasAndPost(mCanvas);
        }
    }

}
