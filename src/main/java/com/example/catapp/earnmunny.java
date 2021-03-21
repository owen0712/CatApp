package com.example.catapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Timer;
import java.util.TimerTask;

public class earnmunny extends AppCompatActivity implements View.OnTouchListener{

    private TextView coin;
    private ImageView cat,w1,w2,w3,w4,w5,w6,w7,cur,cdl,cdr;
    private FrameLayout frame;
    private float catX,catY;
    private boolean action_up,action_down,action_left,action_right;
    private boolean c1=false,c2=false,c3=false;
    private double munny = 0;
    private long gain_time,gain_time2,gain_time3;

    FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=database.getReference();
    FirebaseUser user=firebaseAuth.getCurrentUser();
    String userId=user.getUid();
    cat Cat=new cat();


    private Timer timer = new Timer();
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earnmunny);

        cat = findViewById(R.id.catAnim);
        frame = findViewById(R.id.frame);
        w1 = findViewById(R.id.wall1);
        w2 = findViewById(R.id.wall2);
        w3 = findViewById(R.id.wall3);
        w4 = findViewById(R.id.wall4);
        w5 = findViewById(R.id.wall5);
        w6 = findViewById(R.id.wall6);
        w7 = findViewById(R.id.wall7);
        cur = findViewById(R.id.cur);
        cdl = findViewById(R.id.cdl);
        cdr = findViewById(R.id.cdr);
        coin = findViewById(R.id.coin);

        coin.setText("coin :"+munny);

        findViewById(R.id.upBtn).setOnTouchListener(this);
        findViewById(R.id.downBtn).setOnTouchListener(this);
        findViewById(R.id.leftBtn).setOnTouchListener(this);
        findViewById(R.id.rightBtn).setOnTouchListener(this);

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        changePos();
                        coinchange();
                    }
                });
            }
        },0,1);
    }

    public void coinchange()
    {
        if(c1||c2||c3)
        {
            if((System.currentTimeMillis()/60000)-gain_time >= 0.25)
            {
                cur.setImageAlpha(255);
                c1 = false;
            }
            if((System.currentTimeMillis()/60000)-gain_time2 >= 0.25)
            {
                cdl.setImageAlpha(255);
                c2 = false;
            }
            if((System.currentTimeMillis()/60000)-gain_time3 >= 0.25)
            {
                cdr.setImageAlpha(255);
                c3 = false;
            }
        }
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            showData(dataSnapshot);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });

    }

    public void changePos(){
        catX = cat.getX();
        catY = cat.getY();

        //up
        if(action_up) {
            catY -=3.1;
            if(catX>w1.getX()-cat.getWidth()&&catY<w1.getHeight()) catY = w1.getHeight();
            if(catX>w5.getX()-cat.getWidth()&&catX<w5.getX()+w5.getWidth()&&catY<w5.getY()+w5.getHeight()&&catY>w5.getY()-cat.getHeight())catY = w5.getY()+w5.getHeight();
        }

        //down
        if(action_down){
            catY +=3.1;
            if(catX<w2.getWidth()&&catY>w2.getY()-cat.getHeight())catY = w2.getY()-cat.getHeight();
            if(catY>w3.getY()-cat.getHeight())catY = w3.getY()-cat.getHeight();
            if(catX>w5.getX()-cat.getWidth()&&catX<w5.getX()+w5.getWidth()&&catY<w5.getY()+w5.getHeight()&&catY>w5.getY()-cat.getHeight())catY = w5.getY()-cat.getHeight();
            if(catY>w7.getY()-cat.getHeight())catY = w7.getY()-cat.getHeight();
        }

        //left
        if(action_left){
            catX -=3.1;
            if(catX<w2.getWidth()&&catY>w2.getY()-cat.getHeight())catX = w2.getWidth();
            if(catX>w5.getX()-cat.getWidth()&&catX<w5.getX()+w5.getWidth()&&catY<w5.getY()+w5.getHeight()&&catY>w5.getY()-cat.getHeight())catX = w5.getX()+w5.getWidth();
        }

        //right
        if(action_right){
            catX +=3.1;
            if(catX>w1.getX()-cat.getWidth()&&catY<w1.getHeight())catX = w1.getX()-cat.getWidth();
            if(catX>w4.getX()-cat.getWidth())catX = w4.getX()-cat.getWidth();
            if(catX>w5.getX()-cat.getWidth()&&catX<w5.getX()+w5.getWidth()&&catY<w5.getY()+w5.getHeight()&&catY>w5.getY()-cat.getHeight())catX = w5.getX()-cat.getWidth();
            if(catX>w6.getX()-cat.getWidth())catX = w6.getX()-cat.getWidth();
        }

        //vertical move
        if(catY<0)catY = 0;
        if(catY > frame.getHeight() - cat.getHeight())catY = frame.getHeight() - cat.getHeight();

        //horizontal move
        if(catX<0)catX = 0;
        if(catX > frame.getWidth() - cat.getWidth())catX = frame.getWidth() - cat.getWidth();

        //touch coin
        if(catX>cur.getX()-cat.getWidth()&&catX<cur.getX()+cur.getWidth()&&catY<cur.getY()+cur.getHeight()&&catY>cur.getY()-cat.getHeight())
        {
            if(!c1)
            {
                int coinGain= 1;
                munny ++;
                coin.setText("coin :"+munny);
                gain_time = System.currentTimeMillis()/60000;
                cur.setImageAlpha(0);
                c1 = true;
                Cat.addMoney(coinGain);
                databaseReference.child("Users").child(userId).child("money").setValue(Cat.getMoney());
            }
        }
        else if(catX>cdl.getX()-cat.getWidth()&&catX<cdl.getX()+cdl.getWidth()&&catY<cdl.getY()+cdl.getHeight()&&catY>cdl.getY()-cat.getHeight())
        {
            if(!c2)
            {
                int coinGain= 1;
                munny ++;
                coin.setText("coin :"+munny);
                gain_time2 = System.currentTimeMillis()/60000;
                cdl.setImageAlpha(0);
                c2 = true;
                Cat.addMoney(coinGain);
                databaseReference.child("Users").child(userId).child("money").setValue(Cat.getMoney());
            }
        }
        else if(catX>cdr.getX()-cat.getWidth()&&catX<cdr.getX()+cdr.getWidth()&&catY<cdr.getY()+cdr.getHeight()&&catY>cdr.getY()-cat.getHeight()){
            if(!c3)
            {
                int coinGain= 1;
                munny ++;
                coin.setText("coin :"+munny);
                gain_time3 = System.currentTimeMillis()/60000;
                cdr.setImageAlpha(0);
                c3 = true;
                Cat.addMoney(coinGain);
                databaseReference.child("Users").child(userId).child("money").setValue(Cat.getMoney());
            }
        }

        cat.setX(catX);
        cat.setY(catY);
    }
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent){
        if(motionEvent.getAction()==MotionEvent.ACTION_DOWN){
            switch(view.getId()){
                case R.id.upBtn:
                    action_up = true;
                    break;
                case R.id.downBtn:
                    action_down = true;
                    break;
                case R.id.leftBtn:
                    action_left = true;
                    break;
                case R.id.rightBtn:
                    action_right = true;
                    break;
            }
        }
        else{
            action_up = false;
            action_down = false;
            action_left = false;
            action_right = false;
        }
        return false;
    }


    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds:dataSnapshot.getChildren()){
            Cat=new cat();
            Cat.setMoney(ds.child(userId).getValue(cat.class).getMoney());
        }
    }
    @Override
    public void onBackPressed() {
        Intent chg=new Intent(earnmunny   .this,gameSelection.class);
        startActivity(chg);
    }

}

