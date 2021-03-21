package com.example.catapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button btnLogOut;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String userId;
    private cat Cat=new cat();
    int con=0;
    ImageView imageViewAn;
    AnimationDrawable anim;
    MediaPlayer mediaPlayer;
    ImageView i2;
    ProgressBar pb;
    int happy_limit = 100;
    int hunger_limit = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogOut =findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(MainActivity.this, login.class);
                startActivity(I);
            }
        });

//        int[] exp = {100,200,300,400,500};
        Cat.setHunger(hunger_limit);
        Cat.setHappy(happy_limit);
//        Cat.setExp(0);
        Cat.setTime(System.currentTimeMillis());
        pb = findViewById(R.id.progressBar);
        pb.getProgressDrawable().setColorFilter(Color.RED, PorterDuff.Mode.SRC_IN);
        pb.setProgress((int)(((double)Cat.getHunger()/(double)hunger_limit)*100));
        run();

//        for(int i = 4;i>=0;i--)
//        {
//            if(Cat.getExp()>=exp[i])
//            {
//                double temp = (double)happy_limit * 1.05;
//                Cat.setHunger((int)temp);
//                Cat.setHappy((int)temp);
//                pb.setProgress((int)(((double)Cat.getHunger()/(double)hunger_limit)*100));
//                break;
//            }
//        }
        Button foodBtn=findViewById(R.id.foodBtn);
        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent chg=new Intent(MainActivity.this,kitchen.class);
                startActivity(chg);
            }
        });

        btnLogOut = findViewById(R.id.bedroom);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent I = new Intent(MainActivity.this, bedroom.class);
                startActivity(I);

            }
        });

        Button gameBtn=findViewById(R.id.game);
        gameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gm=new Intent(MainActivity.this,gameSelection.class);
                startActivity(gm);
            }
        });

        firebaseAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        userId=user.getUid();
        Cat=new cat();

        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(MainActivity.this, "User logged in ", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };

        imageViewAn = findViewById(R.id.imageViewAn);
        i2=findViewById(R.id.catAnim);
        imageViewAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer==null){
                    mediaPlayer=MediaPlayer.create(MainActivity.this,R.raw.meow1);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            stopPlayer();
                        }
                    });
                }
                if(con==0) {
                    con=1;
                    i2.setImageAlpha(0);
                    imageViewAn.setImageAlpha(255);
                    if (imageViewAn == null) throw new AssertionError();
                    imageViewAn.setBackgroundResource(R.drawable.cat_animation);
                    anim = (AnimationDrawable) imageViewAn.getBackground();
                    anim.run();
                }
                else if(con==1) {
                    con = 0;
                    anim.stop();
                    imageViewAn.setBackgroundResource(R.drawable.cat2);
                }

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        /*final TextView expDisp=findViewById(R.id.expDisplay);
        int tempNum=Cat.getExp();
        String expTemp=Integer.toString(tempNum);
        expDisp.setText(expTemp);
        Toast.makeText(MainActivity.this, expTemp, Toast.LENGTH_SHORT).show();
        Button addBtn=findViewById(R.id.addButton);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cat.addExp(1);
                databaseReference.child("Users").child(userId).child("exp").setValue(Cat.getExp());
                String tempExp=Integer.toString(Cat.getExp());
                expDisp.setText(tempExp);
            }
        });*/
    }
    //from heng
    public void run()
    {
        final Timer t = new Timer();
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                pb.setProgress((int)(((double)Cat.getHunger()/(double)hunger_limit)*100));
                if(System.currentTimeMillis()-Cat.getTime()>=100000000)
                {
                    Cat.addHappy(-1);
                    Cat.addHunger(-1);
                    databaseReference.child("Users").child(userId).child("happy").setValue(Cat.getHappy());
                    databaseReference.child("Users").child(userId).child("hunger").setValue(Cat.getHunger());
                    Cat.setTime(System.currentTimeMillis());
                }
                if(Cat.getHunger()<=0)
                {
                    t.cancel();
                }
            }
        };
        t.schedule(tt,0,1000);
    }

    private void stopPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            Toast.makeText(this, "Media Player released", Toast.LENGTH_SHORT).show();
        }
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds:dataSnapshot.getChildren()){
            Cat=new cat();
            Cat.setMoney(ds.child(userId).getValue(cat.class).getMoney());
            Cat.setExp(ds.child(userId).getValue(cat.class).getExp());
            Cat.setAge(ds.child(userId).getValue(cat.class).getAge());
            Cat.setColour(ds.child(userId).getValue(cat.class).getColour());
            Cat.setHunger(ds.child(userId).getValue(cat.class).getHunger());
            Cat.setHygiene(ds.child(userId).getValue(cat.class).getHygiene());
            Cat.setName(ds.child(userId).getValue(cat.class).getName());
            Cat.setSick(ds.child(userId).getValue(cat.class).getSick());
            Cat.setSleep(ds.child(userId).getValue(cat.class).getSleep());
            Cat.setTime(ds.child(userId).getValue(cat.class).getTime());
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        GameView.stopMusic();
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(startMain);
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}