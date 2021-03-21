package com.example.catapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class gameSelection extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selection);

        ImageView catFighter=findViewById(R.id.catFighter);
        catFighter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chg=new Intent(gameSelection.this, mouseGame.class);
                startActivity(chg);
            }
        });

        ImageView catWalk=findViewById(R.id.catWalk);
        catWalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chg=new Intent(gameSelection.this,earnmunny.class);
                startActivity(chg);
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent chg=new Intent(gameSelection.this,MainActivity.class);
        startActivity(chg);

    }
}
