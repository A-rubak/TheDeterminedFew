package com.example.thedeterminedfew;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.thedeterminedfew.Abilities.Ability;
import com.example.thedeterminedfew.Components.CardHolderComponent;
import com.example.thedeterminedfew.Components.CharacterAbilityComponent;
import com.example.thedeterminedfew.Components.CharacterStatusComponent;
import com.example.thedeterminedfew.Interfaces.GraphicsComponent;
import com.example.thedeterminedfew.Interfaces.InputComponent;
import com.example.thedeterminedfew.Interfaces.MovementComponent;
import com.example.thedeterminedfew.Interfaces.SpawnComponent;

public class GameObject {
    private Transform mTransform;
    private boolean isActive = false;
    private boolean HUDActive = false;
    private String mTag;
    private GraphicsComponent graphicsComponent;
    private MovementComponent movementComponent;


    private SpawnComponent spawnComponent;
    private CharacterStatusComponent statusComponent = null;
    private CharacterAbilityComponent abilityComponent = null;
    private CardHolderComponent deckComponent = null;
    private int characterSpot;
    private boolean performedAction = false;



    void setSpawner(SpawnComponent s) {
        spawnComponent = s;
    }
    void setGraphics(GraphicsComponent g, Context c, ObjectSpec spec, PointF objectSize) {

        graphicsComponent = g;
        g.initialize(c, spec, objectSize);
    }
    void setMovement(MovementComponent m) {
        movementComponent = m;
    }
    void setInput(InputComponent s) {
        s.setTransform(mTransform);
    }
    void setmTag(String tag) {
        mTag = tag;
    }
    void setTransform(Transform t) {
        mTransform = t;
    }
    void setStatusComponent(CharacterStatusComponent status){
        statusComponent = status;
    }



    void draw(Canvas canvas, Paint paint,boolean highlight) {
        graphicsComponent.draw(canvas, paint, mTransform, statusComponent,highlight);
    }
    void update(long fps, Transform playerTransform) {
        /*if (!(movementComponent.move(fps,
                mTransform, playerTransform))) {
            // Component returned false`
            isActive = false;
        }*/
    }
    boolean spawn() {
        // Only spawnComponent if not already active
        if (!isActive) {
            spawnComponent.spawn(mTransform);
            isActive = true;

            return true;

        }
        return false;
    }

    boolean checkActive() {
        return isActive;
    }

    public String getTag() {
        return mTag;
    }

    void setInactive() {
        isActive = false;
    }

    public Transform getTransform() {
        return mTransform;
    }


    public void setAbilityComponent(CharacterAbilityComponent abilityComponent) {
        this.abilityComponent = abilityComponent;
    }

    public CharacterAbilityComponent getAbilityComponent() {
        return abilityComponent;
    }

    public int getCharacterSpot() {
        return characterSpot;
    }

    public void setCharacterSpot(int characterSpot) {
        this.characterSpot = characterSpot;
    }

    public boolean isHUDActive() {
        return HUDActive;
    }

    public void setHUDActive(boolean status) {
        HUDActive = status;
    }

    public void getAttacked(Ability a){
        statusComponent.changeHealthStatus(a.getInfluencePoints());

    }

    public boolean performedAction() {
        return performedAction;
    }

    public void setPerformedAction(boolean performedAction) {
        this.performedAction = performedAction;
    }

    public boolean checkDeathStatus() {
        if(statusComponent.getHealth() <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    public CardHolderComponent getCardComponent() {
        return deckComponent;
    }

    public void setDeckComponent(CardHolderComponent component) {
        deckComponent = component;
    }



}

