package com.example.thedeterminedfew.Levels;

import android.content.Context;
import android.graphics.PointF;

import com.example.thedeterminedfew.CharacterSpots;
import com.example.thedeterminedfew.GameEngine;
import com.example.thedeterminedfew.GameObject;
import com.example.thedeterminedfew.GameObjectFactory;
import com.example.thedeterminedfew.Level;
import com.example.thedeterminedfew.ObjectSpecifications.BackgroundSpec;
import com.example.thedeterminedfew.ObjectSpecifications.CharacterSpecCinder;
import com.example.thedeterminedfew.ObjectSpecifications.CharacterSpecJewel;
import com.example.thedeterminedfew.ObjectSpecifications.CharacterSpecMain;
import com.example.thedeterminedfew.ObjectSpecifications.EnemySpecGuard;

import java.util.ArrayList;

public class LevelChapterTwo extends Level {
    // Keep track of specific types
    public static final int BACKGROUND_INDEX = 0;


    // This will hold all the instances of GameObject

    private ArrayList<GameObject> objects;

    public LevelChapterTwo(Context context, PointF mScreenSize, GameEngine ge){
        super();

        objects = new ArrayList<>();
        GameObjectFactory factory = new GameObjectFactory(context, mScreenSize, ge);

        buildGameObjects(factory);

    }

    ArrayList<GameObject> buildGameObjects(GameObjectFactory factory){

        objects.clear();
        objects.add(BACKGROUND_INDEX, factory.create(new BackgroundSpec()));

        objects.add(factory.create(new CharacterSpecCinder(CharacterSpots.FIRST)));
        objects.add(factory.create(new CharacterSpecMain(CharacterSpots.SECOND)));
        objects.add(factory.create(new EnemySpecGuard(CharacterSpots.FIRST)));
        objects.add(factory.create(new EnemySpecGuard(CharacterSpots.SECOND)));
        //objects.add(factory.create(new EnemySpecGuard(CharacterSpots.SECOND)));


        return objects;
    }
    public ArrayList<GameObject> getGameObjects(){
        return objects;
    }
}

