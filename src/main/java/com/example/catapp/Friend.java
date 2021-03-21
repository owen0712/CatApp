package com.example.catapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Manish on 10/24/2016.
 */

public class Friend {

    private Bitmap bitmap;
    private int x;
    private int y;
    private int speed = 1;

    private int maxX;
    private int minX;

    private int maxY;
    private int minY;

    //creating a rect object for a friendly ship
    private Rect detectCollision;


    public Friend(Context context, int screenX, int screenY) {
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.friend);
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;
        Random generator = new Random();
        speed = generator.nextInt(6) + 5;
        y = screenY;
        x = generator.nextInt(maxX) - bitmap.getWidth();

        //initializing rect object
        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void update(int playerSpeed) {
        y -= playerSpeed;
        y -= speed;
        if (y < minY - bitmap.getHeight()) {
            Random generator = new Random();
            speed = generator.nextInt(10) + 5;
            y = maxY;
            x = generator.nextInt(maxX) - bitmap.getWidth();
        }
        if (x < minX) {
            x = minX;
        }
        if (x > maxX) {
            x = maxX;
        }
        //Adding the top, left, bottom and right to the rect object
        detectCollision.left = y;
        detectCollision.top = x;
        detectCollision.right = y + bitmap.getHeight();
        detectCollision.bottom = x + bitmap.getWidth();
    }

    //one more getter for getting the rect object
    public Rect getDetectCollision() {
        return detectCollision;
    }

    //getters
    public Bitmap getBitmap() {
        return bitmap;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSpeed() {
        return speed;
    }
}
