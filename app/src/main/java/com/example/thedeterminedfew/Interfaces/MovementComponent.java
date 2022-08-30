package com.example.thedeterminedfew.Interfaces;

import com.example.thedeterminedfew.Transform;

public interface MovementComponent {
    boolean move(long fps, Transform t,
                 Transform playerTransform);
}
