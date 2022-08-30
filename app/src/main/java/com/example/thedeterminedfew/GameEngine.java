package com.example.thedeterminedfew;

import android.content.Context;
import android.graphics.Point;
import android.graphics.PointF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceView;

import com.example.thedeterminedfew.HUD.HUDController;
import com.example.thedeterminedfew.Interfaces.GameEngineBroadcaster;
import com.example.thedeterminedfew.Interfaces.GameMaster;
import com.example.thedeterminedfew.Interfaces.InputObserver;
import com.example.thedeterminedfew.Levels.LevelChapterOne;
import com.example.thedeterminedfew.Levels.LevelChapterThree;
import com.example.thedeterminedfew.Levels.LevelChapterTwo;
import com.example.thedeterminedfew.Levels.MainMenu;
import com.example.thedeterminedfew.UI.UIController;

import java.util.ArrayList;

public class GameEngine extends SurfaceView implements Runnable, GameMaster, GameEngineBroadcaster {
    private Thread mThread = null;
    private long mFPS;

    private ArrayList<InputObserver> inputObservers = new ArrayList();

    UIController mUIController;
    private GameState mGameState;
    private SoundEngine mSoundEngine;
    HUDController mHUD;
    Renderer mRenderer;
    ParticleSystem mParticleSystem;
    PhysicsEngine mPhysicsEngine;
    Level mLevel;
    Level mPlayingLevel;
    Point mSize;
    private boolean rendererThreadRunning = false;



    public GameEngine(Context context, Point size) {
        super(context);

        mUIController = new UIController(this);
        mSoundEngine = new SoundEngine(context);
        mGameState = new GameState(this, context);

        mHUD = new HUDController(size);
        mRenderer = new Renderer(this);
        mParticleSystem = new ParticleSystem();
        mPhysicsEngine = new PhysicsEngine();
        // Even just 10 particles look good
        // But why have less when you can have more
        mParticleSystem.init(1000);
        mSize = size;
        mLevel = new MainMenu(this.getContext(), new PointF(mSize.x, mSize.y), this);





    }

    @Override
    public void run() {
        /*while (mGameState.getThreadRunning()) {

            long frameStartTime = System.currentTimeMillis();

                //ArrayList<GameObject> objects = mLevel.getGameObjects();
                //mRenderer.draw(objects, mGameState, mHUD, mParticleSystem);


            if (!mGameState.getPaused()) {
                // Update all the game objects here
                // in a new way

                // This call to update will evolve with the project
                //if(mPhysicsEngine.update(mFPS, objects, mGameState, mSoundEngine, mParticleSystem)){

                    // Player hit
                    //deSpawnReSpawn();
                //}
            }
            // Update all the game objects here
            // in a new way

            // Draw all the game objects here
            // in a new way

            // Measure the frames per second in the usual way
            long timeThisFrame = System.currentTimeMillis()
                    - frameStartTime;
            if (timeThisFrame >= 1) {
                final int MILLIS_IN_SECOND = 1000;
                mFPS = MILLIS_IN_SECOND / timeThisFrame;
            }

        }*/
    }

    private void startRenderer(){
        Thread renderer = new Thread(new Runnable() {
            @Override
            public void run() {
                while(mGameState.getThreadRunning()){
                    ArrayList<GameObject> objects = mLevel.getGameObjects();
                    mRenderer.draw(objects, mGameState, mHUD, mParticleSystem);
                }


            }
        });
        renderer.start();
    }
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {
        // Handle the player's input here
        // But in a new way
        Log.d("Debug", "We do be klicking in engine");
        ArrayList<InputObserver> mimicObservers = new ArrayList<>();
        for (InputObserver o: inputObservers) {
            mimicObservers.add(o);
        }
        for (InputObserver o : mimicObservers) {
            o.handleInput(motionEvent, mGameState, mHUD.getCurrentControls(mGameState));
        }

        return true;
    }
    public void stopThread() {
        // New code here soon
        mGameState.stopEverything();
        try {
            mThread.join();
        } catch (InterruptedException e) {
            Log.e("Exception","stopThread()"
                    + e.getMessage());
        }
    }
    public void startThread() {
        // New code here soon
        mGameState.startThread();
        mThread = new Thread(this);
        mThread.start();
        startRenderer();
    }

    @Override
    public void deSpawnReSpawn(int chapter) {
        mLevel = retrieveLevel(chapter);
        ArrayList<GameObject> objects = mLevel.getGameObjects();
        ArrayList<GameObject> playerCharacters = new ArrayList<>();
        ArrayList<GameObject> enemyCharacters = new ArrayList<>();
        for(GameObject o : objects){
            if(o.getTag().equals("Ally")){
                playerCharacters.add(o);
            }else if(o.getTag().equals("Enemy")){
                enemyCharacters.add(o);
            }
            o.setInactive();
            Log.d("Debug","Spawning this :" + o.getTag());
            o.spawn();
        }

        mGameState.saveCharacterLists(playerCharacters,enemyCharacters);
        mHUD.createNewLevel(getContext(),playerCharacters);

        //objects.get(LevelChapterOne.PLAYER_INDEX).spawn(objects.get(LevelChapterOne.PLAYER_INDEX).getTransform());

        //objects.get(LevelChapterOne.BACKGROUND_INDEX).spawn(objects.get(LevelChapterOne.PLAYER_INDEX).getTransform());
    }

    private Level retrieveLevel(int chapter) {
        switch(chapter){
            case 1:return new LevelChapterOne(this.getContext(), new PointF(mSize.x, mSize.y), this);
            case 2:return new LevelChapterTwo(this.getContext(), new PointF(mSize.x, mSize.y), this);
            case 3:return new LevelChapterThree(this.getContext(), new PointF(mSize.x, mSize.y), this);
        }
        return null;
    }

    @Override
    public void changeHud() {
        mHUD.turnOffAbilityHud();
    }

    @Override
    public void winCondition() {
        mLevel = new MainMenu(this.getContext(), new PointF(mSize.x, mSize.y), this);
        mHUD.resetPlayingLevel();
        inputObservers.clear();
        mUIController.reAddObserver(this);

        mGameState.changeScene();
        mGameState.setCurrentGameState(CurrentGameState.WIN);
    }

    @Override
    public void loseCondition() {
        mLevel = new MainMenu(this.getContext(), new PointF(mSize.x, mSize.y), this);
        mHUD.resetPlayingLevel();
        inputObservers.clear();
        mUIController.reAddObserver(this);
        mGameState.changeScene();
        mGameState.setCurrentGameState(CurrentGameState.LOSE);
    }

    @Override
    public void playMusic() {
        mSoundEngine.playMusic(getContext());
    }

    @Override
    public void stopMusic() {
        mSoundEngine.stopMusic();
    }

    @Override
    public void addObserver(InputObserver o) {
        inputObservers.add(o);
    }



    /*@Override
    public boolean spawnPlayerLaser(Transform transform) {
        ArrayList<GameObject> objects = mLevel.getGameObjects();

        if (objects.get(Level.mNextPlayerLaser).spawn(transform)) {

            Level.mNextPlayerLaser++;
            mSoundEngine.playShoot();
            if (Level.mNextPlayerLaser == Level.LAST_PLAYER_LASER + 1) {

                // Just used the last laser
                Level.mNextPlayerLaser = Level.FIRST_PLAYER_LASER;
            }
        }
        return true;

    }*/
}
