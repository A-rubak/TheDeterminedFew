package com.example.thedeterminedfew;

import android.content.Context;
import android.graphics.PointF;

import com.example.thedeterminedfew.Components.BackgroundGraphicsComponent;
import com.example.thedeterminedfew.Components.BackgroundMovementComponent;
import com.example.thedeterminedfew.Components.BackgroundSpawnComponent;
import com.example.thedeterminedfew.Components.CardHolderComponent;
import com.example.thedeterminedfew.Components.CharacterAbilityComponent;
import com.example.thedeterminedfew.Components.CharacterStatusComponent;
import com.example.thedeterminedfew.Components.EnemyInputComponent;
import com.example.thedeterminedfew.Components.EnemySpawnComponent;
import com.example.thedeterminedfew.Components.LaserMovementComponent;
import com.example.thedeterminedfew.Components.LaserSpawnComponent;
import com.example.thedeterminedfew.Components.MainCharacterInputComponent;
import com.example.thedeterminedfew.Components.PlayerInputComponent;
import com.example.thedeterminedfew.Components.PlayerMovementComponent;
import com.example.thedeterminedfew.Components.PlayerSpawnComponent;
import com.example.thedeterminedfew.Components.StdGraphicsComponent;

public class GameObjectFactory {

    private Context mContext;
    private PointF mScreenSize;
    private GameEngine mGameEngineReference;

    public GameObjectFactory(Context c, PointF screenSize, GameEngine gameEngine) {

        this.mContext = c;
        this.mScreenSize = screenSize;
        mGameEngineReference = gameEngine;
    }

    public GameObject create(ObjectSpec spec) {
        GameObject object = new GameObject();
        int numComponents = spec.getComponents().length;
        final float HIDDEN = -2000f;
        object.setmTag(spec.getTag());
        // Configure the speed relative to the screen size
        float speed = mScreenSize.x / spec.getSpeed();
        // Configure the object size relative to screen
        //size
        PointF objectSize = new PointF(mScreenSize.x / spec.getScale().x, mScreenSize.y / spec.getScale().y);
        // Set the location to somewhere off-screen
        PointF location = new PointF(HIDDEN, HIDDEN);
        object.setTransform(new Transform(speed, objectSize.x, objectSize.y, location, mScreenSize));
        if(spec.getObjectSpot() != null) {
            object.setCharacterSpot(spec.getObjectSpot().ordinal());
        }

        // More code here next...

        // Loop through and add/initialize all the components
        for (int i = 0; i < numComponents; i++) {
            switch (spec.getComponents()[i]) {
                case "PlayerInputComponent":
                    object.setInput(new PlayerInputComponent(mGameEngineReference,object));
                    break;
                case "MainCharacterInputComponent":
                    object.setInput(new MainCharacterInputComponent(mGameEngineReference,object));
                    break;
                case "StdGraphicsComponent":
                    object.setGraphics(new StdGraphicsComponent(spec.getAnimationPoints(), object), mContext, spec, objectSize);
                    break;
                case "PlayerMovementComponent":
                    object.setMovement(new PlayerMovementComponent());
                    break;
                case "LaserMovementComponent":
                    object.setMovement(new LaserMovementComponent());
                    break;
                case "PlayerSpawnComponent":
                    object.setSpawner(new PlayerSpawnComponent(spec.getObjectSpot()));
                    break;
                case "LaserSpawnComponent":
                    object.setSpawner(new LaserSpawnComponent());
                    break;
                case "BackgroundGraphicsComponent":
                    object.setGraphics(new BackgroundGraphicsComponent(), mContext, spec, objectSize);
                    break;
                case "BackgroundMovementComponent":
                    object.setMovement(new BackgroundMovementComponent());
                    break;
                case "BackgroundSpawnComponent":
                    object.setSpawner(new BackgroundSpawnComponent());
                    break;
                case "CharacterStatusComponent":
                    object.setStatusComponent(new CharacterStatusComponent(spec.getHealth(),object.getTransform()));
                    break;
                case "CharacterAbilityComponent":
                    object.setAbilityComponent(new CharacterAbilityComponent(spec.getAbilityList()));
                    break;
                case "CardHolderComponent":
                    object.setDeckComponent(new CardHolderComponent(spec.getDeck()));
                    break;
                case "EnemyInputComponent":
                    object.setInput(new EnemyInputComponent(mGameEngineReference,object));
                    break;
                case "EnemySpawnComponent":
                    object.setSpawner(new EnemySpawnComponent(spec.getObjectSpot()));
                    break;

                default:
                    // Error unidentified component
                    break;
            }
        }
        // Return the completed GameObject to the Level class
        return object;
    }
}
