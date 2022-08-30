package com.example.thedeterminedfew.Components;

import com.example.thedeterminedfew.Interfaces.SpawnComponent;
import com.example.thedeterminedfew.Transform;

public class BackgroundSpawnComponent implements SpawnComponent {
        @Override
        public void spawn(Transform t) {
        // Place the background in the top left corner
            t.setLocation(0f,0f);
        }

}

