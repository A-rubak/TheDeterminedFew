package com.example.thedeterminedfew;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.util.Log;

import com.example.thedeterminedfew.Abilities.Ability;
import com.example.thedeterminedfew.Interfaces.GameMaster;
import com.example.thedeterminedfew.SpellCards.Card;

import java.util.ArrayList;

public final class GameState {
    private static volatile boolean mThreadRunning = false;
    private static volatile boolean mPaused = true;
    private static volatile boolean mGameOver = true;
    private static volatile boolean mDrawing = false;
    public static int MAX_MERGE_SIZE = 2;


    // This object will have access to the deSpawnReSpawn
    // method in GameEngine- once it is initialized
    private GameMaster gameMaster;
    private int mScore;
    private int mHighScore;
    private int mNumShips;
    private CurrentGameState mCurrentGameState;
    private Ability mChosenAbility = null;
    private GameObject mChosenEnemy = null;
    private GameObject mChosenAlly = null;
    private ArrayList<Card> cardsToMerge = new ArrayList<>();
    private ArrayList<GameObject> allyList = null;
    private ArrayList<GameObject> enemyList = null;
    private PointF fingerPosition;

    private boolean musicState = false;
    private boolean isMergeWindowActive = false;


    // This is how we will make all the high scores persist
    private SharedPreferences.Editor mEditor;
    private boolean updateCards = false;

    GameState(GameMaster gs, Context context){
        // This initializes the gameMaster reference
        gameMaster = gs;

        // Get the current high score
        SharedPreferences prefs;
        prefs = context.getSharedPreferences("HiScore",
                Context.MODE_PRIVATE);
        // Initialize the mEditor ready
        mEditor = prefs.edit();
        // Load high score from a entry in the file
        // labeled "hiscore"
        // if not available highscore set to zero 0
        mHighScore = prefs.getInt("hi_score", 0);
        mCurrentGameState = CurrentGameState.MAIN_MENU;

        turnMusicOnOff();
    }



    private void endGame() {
        mGameOver = true;
        mPaused = true;
        if (mScore > mHighScore) {
            mHighScore = mScore;
            // Save high score
            mEditor.putInt("hi_score", mHighScore);
            mEditor.commit();
        }
    }

    public void startNewGame(int chapter){
        mScore = 0;
        mNumShips = 3;
        // Don't want to be drawing objects
        // while deSpawnReSpawn is
        // clearing them and spawning them again
        stopDrawing();
        gameMaster.deSpawnReSpawn(chapter);
        resume();
        // Now we can draw again
        startDrawing();
    }

    void changeScene(){

        // Don't want to be drawing objects
        // while deSpawnReSpawn is
        // clearing them and spawning them again
        stopDrawing();
        endGame();
        // Now we can draw again
        startDrawing();
    }

    void loseLife(SoundEngine se){
        mNumShips--;
        se.playPlayerExplode();
        if(mNumShips == 0){
            pause();
            endGame();
        }
    }

    int getNumShips(){
        return mNumShips;
    }
    void increaseScore(){
        mScore++;
    }
    int getScore(){
        return mScore;
    }
    int getHighScore(){
        return mHighScore;
    }

    void pause(){
        mPaused = true;
    }
    void resume(){
        mGameOver = false;
        mPaused = false;
    }
    void stopEverything(){
        mPaused = true;
        mGameOver = true;
        mThreadRunning = false;
    }

    void startThread(){
        mThreadRunning = true;
    }

    private void stopDrawing(){
        mDrawing = false;
    }
    private void startDrawing(){
        mDrawing = true;
    }
    boolean getDrawing() {
        return mDrawing;
    }
    public boolean getPaused(){
        return mPaused;
    }
    public boolean getGameOver(){
        return mGameOver;
    }

    boolean getThreadRunning() {
        return mThreadRunning;
    }

    public CurrentGameState getCurrentGameState() {
        return mCurrentGameState;
    }

    public void setCurrentGameState(CurrentGameState state){
        mCurrentGameState = state;
    }

    public void changeHud() {
        gameMaster.changeHud();
    }


    public Ability getChosenAbility() {
        return mChosenAbility;
    }

    public void setChosenAbility(Ability a) {
        mChosenAbility = a;
    }

    public GameObject getChosenEnemy() {
        return mChosenEnemy;
    }

    public void setChosenEnemy(GameObject o) {
        mChosenEnemy = o;
    }


    public void performAction(){
        getChosenEnemy().getAttacked(getChosenAbility());
        setChosenEnemy(null);
        setChosenAbility(null);
        mChosenAlly.setPerformedAction(true);
        mChosenAlly.setHUDActive(false);
        mChosenAlly = null;
        checkCharacterDeath();
    }

    public void setChosenAlly(GameObject ally) {
        mChosenAlly = ally;
    }
    public GameObject getChosenAlly() {
        return mChosenAlly;
    }

    public void enemyAction() {
        if(allyList.isEmpty() || enemyList.isEmpty()){
            return;
        }
        for (GameObject enemy:enemyList) {
            ArrayList<Ability> abilityList = enemy.getAbilityComponent().getAbilityList();
            int index = (int)(Math.random() * abilityList.size());
            Ability preparedAttack = abilityList.get(index);

            index = (int)(Math.random() * allyList.size());
            if(allyList.isEmpty()){
                return;
            }
            GameObject attackedAlly = allyList.get(index);

            attackedAlly.getAttacked(preparedAttack);
            checkCharacterDeath();

        }

        for (GameObject ally:allyList) {
            ally.setPerformedAction(false);
        }
    }
    public void drawCards(){
        for (GameObject ally:allyList) {
            if(ally.getCardComponent() != null){
                ally.getCardComponent().drawCards();
            }
        }
    }
    private void checkCharacterDeath() {
        if(allyList.isEmpty() || enemyList.isEmpty()){
            return;
        }
        ArrayList<GameObject> objectsToRemove = new ArrayList<>();
        for (GameObject ally:allyList) {
            if(ally.checkDeathStatus()){
                objectsToRemove.add(ally);
                ally.setInactive();
            }
        }
        for (GameObject o: objectsToRemove) {
            allyList.remove(o);
            if(allyList.isEmpty()){

                gameMaster.loseCondition();
                clearVariables();
                return;
            }
        }
        for (GameObject enemy:enemyList) {
            if(enemy.checkDeathStatus()){
                objectsToRemove.add(enemy);
                enemy.setInactive();
            }

        }
        for (GameObject o: objectsToRemove) {
            enemyList.remove(o);
            if(enemyList.isEmpty()){

                gameMaster.winCondition();
                clearVariables();
                return;
            }
        }
    }

    public void saveCharacterLists(ArrayList<GameObject> playerCharacters, ArrayList<GameObject> enemyCharacters) {
        Log.d("Debug","Setting new lists");
        allyList = playerCharacters;
        enemyList = enemyCharacters;

    }

    public ArrayList<GameObject> getEnemyList() {
        return enemyList;
    }

    public void setFingerPosition(PointF position) {
        fingerPosition = position;
    }
    public PointF getFingerPosition() {
        return fingerPosition;
    }

    public void changeMergeActiveStatus() {
        isMergeWindowActive = !isMergeWindowActive;
    }

    public boolean isMergeWindowActive(){
        return isMergeWindowActive;
    }

    public void addCardToMerge(Card c) {

        if(cardsToMerge.size() < MAX_MERGE_SIZE){
            cardsToMerge.add(c);
            updateCards = true;
        }
    }

    public void removeCardToMerge(Card c) {

        if(cardsToMerge.size() > 0){
            cardsToMerge.remove(c);
            updateCards = true;
        }
    }

    public boolean isUpdateCards() {
        return updateCards;
    }

    public void setUpdateCards(boolean updateCards) {
        this.updateCards = updateCards;
    }

    public ArrayList<Card> getCardsToMerge(){
        return cardsToMerge;
    }

    public ArrayList<GameObject> getAllyList(){
        return allyList;
    }

    public void emptyMergeList() {
        Log.d("Debug","Emptying merge list");
        if(cardsToMerge.isEmpty()){
            Log.d("Debug","List do be empty");
            return;
        }
        else{
            for (GameObject object: allyList) {
                if(object.getCardComponent() != null){
                    object.getCardComponent().getDeck().addAll(cardsToMerge);
                }
            }
            cardsToMerge.clear();

        }

    }

    public void mergeCards() {
        Card mergedCard =  cardsToMerge.get(0).merge(cardsToMerge.get(1));
        if(mergedCard != null){
            cardsToMerge.clear();
            cardsToMerge.add(mergedCard);
            emptyMergeList();
            updateCards = true;
            isMergeWindowActive = false;
        }

    }

    public boolean isMusicState() {
        return musicState;
    }

    public void setMusicState() {
        musicState = !musicState;
        turnMusicOnOff();

    }

    private void turnMusicOnOff() {
        if(musicState){Log.d("Debug", "Turning on music");gameMaster.playMusic(); }
        else{gameMaster.stopMusic();}
    }

    public void clearVariables(){
        mChosenAbility = null;
        mChosenEnemy = null;
        mChosenAlly = null;
        //cardsToMerge = null;
        //allyList = new ArrayList<>();
        //enemyList = new ArrayList<>();
    }
}
