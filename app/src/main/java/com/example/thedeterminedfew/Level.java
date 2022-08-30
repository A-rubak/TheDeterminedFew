package com.example.thedeterminedfew;

import android.content.Context;
import android.graphics.PointF;
import java.util.ArrayList;
public abstract class Level {

    private ArrayList<GameObject> objects;

    public Level(){

    }

    ArrayList<GameObject> buildGameObjects(GameObjectFactory factory){
        return objects;
    }
    public ArrayList<GameObject> getGameObjects(){
        return objects;
    }

}
