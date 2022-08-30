package com.example.thedeterminedfew;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.Log;


/**
 * HealthBar display the players health to the screen
 */
public class HealthBar {

    private Paint borderPaint, healthPaint;
    private int width, height; // pixel value

    public HealthBar(Transform t) {

        this.width = (int)t.getmScreenSize().x/10;
        this.height = (int)t.getmScreenSize().y/5;

        this.borderPaint = new Paint();
        int borderColor = Color.GRAY;
        borderPaint.setColor(borderColor);

        this.healthPaint = new Paint();
        int healthColor = Color.GREEN;
        healthPaint.setColor(healthColor);
    }

    public void draw(Canvas canvas,Paint p, Transform characterTransform, float healthPointPercentage) {
        healthPaint.setColor(Color.argb(p.getAlpha(),0,255,0));
        borderPaint.setColor(Color.argb(p.getAlpha(),128,128,128));
        float distanceToPlayer = 30;
        PointF location = characterTransform.getLocation();
        PointF characterSize = characterTransform.getSize();

        // Draw border
        float borderLeft, borderTop, borderRight, borderBottom;
        borderLeft = location.x;
        borderRight = location.x + width;
        borderBottom = location.y + characterSize.y;
        borderTop = characterSize.y + height;
        canvas.drawRect( borderLeft,borderTop,borderRight,borderBottom,borderPaint);

        // Draw health
        float  healthRight;
        healthRight = borderLeft + width*healthPointPercentage;
        canvas.drawRect(borderLeft,borderTop,healthRight,borderBottom, healthPaint);
    }
}
