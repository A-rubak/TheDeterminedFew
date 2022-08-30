package com.example.thedeterminedfew.Interfaces;

import android.graphics.Rect;
import android.view.MotionEvent;

import com.example.thedeterminedfew.GameState;

import java.util.ArrayList;

public interface InputObserver {
    void handleInput(MotionEvent event, GameState gs, ArrayList<Rect> controls);

}
