package com.example.catapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class kitchen extends AppCompatActivity {

    int con=0;
    AnimationDrawable anim;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitchen);

        final ImageView cat=findViewById(R.id.catKitchen);
        final ImageView tempCat=findViewById(R.id.tempCat);
        cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer==null){
                    mediaPlayer= MediaPlayer.create(kitchen.this,R.raw.meow1);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            stopPlayer();
                        }
                    });
                }
                if(con==0) {
                    con=1;
                    tempCat.setImageAlpha(0);
                    cat.setImageAlpha(255);
                    if (cat == null) throw new AssertionError();
                    cat.setBackgroundResource(R.drawable.cat_animation2);
                    anim = (AnimationDrawable) cat.getBackground();
                    anim.run();
                }
                else if(con==1) {
                    con = 0;
                    anim.stop();
                    cat.setBackgroundResource(R.drawable.cat2);
                }
            }
        });

        ImageView fridge=findViewById(R.id.fridge);
        fridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chg=new Intent(kitchen.this,food.class);
                startActivity(chg);
            }
        });
    }

    private void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            Toast.makeText(this, "Media Player released", Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        Intent chg=new Intent(kitchen.this,MainActivity.class);
        startActivity(chg);
    }
}
