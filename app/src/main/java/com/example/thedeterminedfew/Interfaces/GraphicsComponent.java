package com.example.thedeterminedfew.Interfaces;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;

import com.example.thedeterminedfew.Components.CharacterStatusComponent;
import com.example.thedeterminedfew.ObjectSpec;
import com.example.thedeterminedfew.Transform;


public interface GraphicsComponent {
    void initialize(Context c,
                    ObjectSpec s,
                    PointF screensize);

    void draw(Canvas canvas,
              Paint paint,
              Transform t, CharacterStatusComponent statusComponent,boolean highlight);

}
