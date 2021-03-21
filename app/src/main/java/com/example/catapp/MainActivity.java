package com.example.catapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button btnLogOut;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseDatabase database;
    private DatabaseReference databaseReference;
    private String userId;
    private cat Cat=new cat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnLogOut = (Button) findViewById(R.id.btnLogOut);
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseAuth.getInstance().signOut();
                Intent I = new Intent(MainActivity.this, login.class);
                startActivity(I);

            }
        });



        firebaseAuth=FirebaseAuth.getInstance();
        database=FirebaseDatabase.getInstance();
        databaseReference=database.getReference();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        userId=user.getUid();
        Cat=new cat();
        //databaseReference.child("test").push().setValue(Cat);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds:dataSnapshot.getChildren()){
            Cat=new cat();
            Cat.setExp(ds.child(userId).getValue(cat.class).getExp());
            Cat.setAge(ds.child(userId).getValue(cat.class).getAge());
            Cat.setColour(ds.child(userId).getValue(cat.class).getColour());
            Cat.setHunger(ds.child(userId).getValue(cat.class).getHunger());
            Cat.setHygiene(ds.child(userId).getValue(cat.class).getHygiene());
            Cat.setName(ds.child(userId).getValue(cat.class).getName());
            Cat.setSick(ds.child(userId).getValue(cat.class).getSick());
            Cat.setSleep(ds.child(userId).getValue(cat.class).getSleep());
        }
    }

}