package com.example.catapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.Random;

/**
 * Created by Belal on 6/15/2016.
 */

public class Enemy {
    //bitmap for the enemy
    //we have already pasted the bitmap in the drawable folder
    private Bitmap bitmap;

    //x and y coordinates
    private int x;
    private int y;

    //enemy speed
    private int speed = 1;

    //min and max coordinates to keep the enemy inside the screen
    private int maxX;
    private int minX;

    private int maxY;
    private int minY;

    //creating a rect object
    private Rect detectCollision;

    public Enemy(Context context, int screenX, int screenY) {
        //getting bitmap from drawable resource
        bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.enemy);

        //initializing min and max coordinates
        maxX = screenX;
        maxY = screenY;
        minX = 0;
        minY = 0;

        //generating a random coordinate to add enemy
        Random generator = new Random();
        speed = generator.nextInt(6)+5 ;
        y = screenY;
        x = generator.nextInt(maxX) - bitmap.getWidth();

        //initializing rect object
        detectCollision = new Rect(x, y, bitmap.getWidth(), bitmap.getHeight());
    }

    public void update(int playerSpeed) {
        //decreasing x coordinate so that enemy will move right to left
        y -= playerSpeed;
        y -= speed;
        //if the enemy reaches the left edge
        if (y < minY - bitmap.getHeight()) {
            //adding the enemy again to the right edge
            Random generator = new Random();
            speed = generator.nextInt(10)+5 ;
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

    //adding a setter to x coordinate so that we can change it after collision
    public void setY(int y){
        this.y = y;
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
