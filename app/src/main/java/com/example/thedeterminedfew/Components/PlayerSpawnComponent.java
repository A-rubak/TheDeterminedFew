package com.example.thedeterminedfew.Components;

import com.example.thedeterminedfew.CharacterSpots;
import com.example.thedeterminedfew.Interfaces.SpawnComponent;
import com.example.thedeterminedfew.Transform;

public class PlayerSpawnComponent implements SpawnComponent {

    private CharacterSpots currentSpot;
    public PlayerSpawnComponent(CharacterSpots spot){
        currentSpot = spot;
    }


    @Override
    public void spawn(Transform t) {
        int characterPadding = (int)t.getmScreenSize().x / 8;
        int startXPosition = (int)(t.getmScreenSize().x/2 - (t.getmScreenSize().x/2)/3);
        int startYPosition = (int)(t.getmScreenSize().y/2 - (t.getmScreenSize().y/2)/1.5);
        switch(currentSpot){
            case FIRST:  t.setLocation(startXPosition,startYPosition);break;
            case SECOND: t.setLocation(startXPosition - characterPadding  ,startYPosition);break;
            case THIRD:  t.setLocation(startXPosition - characterPadding*2,startYPosition);break;
            case FOURTH: t.setLocation(startXPosition - characterPadding*3,startYPosition);break;
        }

    }
}
