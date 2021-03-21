package com.example.catapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class mouseGame extends AppCompatActivity implements View.OnClickListener{

    //image button
    private ImageButton buttonPlay;
    //high score button
    private ImageButton buttonScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mouse_game);

        //setting the orientation to landscape
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        //getting the button
        buttonPlay = (ImageButton) findViewById(R.id.buttonPlay);

        //initializing the highscore button
        buttonScore = (ImageButton) findViewById(R.id.buttonScore);

        //adding a click listener
        buttonPlay.setOnClickListener(this);

        //setting the on click listener to high score button
        buttonScore.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == buttonPlay) {
            //the transition from MainActivity to GameActivity
            startActivity(new Intent(mouseGame.this, GameActivity.class));
        }
        if (v == buttonScore) {
            //the transition from MainActivity to HighScore activity
            startActivity(new Intent(mouseGame.this, HighScore.class));
        }
    }

    @Override
    public void onBackPressed() {
        Intent chg=new Intent(mouseGame.this,gameSelection.class);
        startActivity(chg);

    }
}
