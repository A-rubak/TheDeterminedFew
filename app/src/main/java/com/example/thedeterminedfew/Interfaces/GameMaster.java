package com.example.thedeterminedfew.Interfaces;

public interface GameMaster {
    // This allows the State class to
    // spawn and despawn objects via the game engine
    public void deSpawnReSpawn(int chapter);

    void changeHud();

    void winCondition();

    void loseCondition();

    void playMusic();
    void stopMusic();

}
