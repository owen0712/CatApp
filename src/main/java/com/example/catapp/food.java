package com.example.catapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class food extends AppCompatActivity {

    ImageView burger,doughnut,iceCream,fish,milk,spaghetti,chicken,pizza;
    cat Cat=new cat();

    FirebaseAuth firebaseAuth= FirebaseAuth.getInstance();
    FirebaseDatabase database= FirebaseDatabase.getInstance();
    DatabaseReference databaseReference=database.getReference();
    FirebaseUser user=firebaseAuth.getCurrentUser();
    String userId=user.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);
        burger=findViewById(R.id.burger);
        doughnut=findViewById(R.id.doughnut);
        iceCream=findViewById(R.id.iceCream);
        fish=findViewById(R.id.fish);
        milk=findViewById(R.id.milk);
        spaghetti=findViewById(R.id.spaghetti);
        chicken=findViewById(R.id.chicken);
        pizza=findViewById(R.id.pizza);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        burger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(food.this);
                builder.setMessage("Are you sure you want to buy this?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Cat.addMoney(-15);
                                Cat.addHappy(10);
                                Cat.addHunger(10);
                                databaseReference.child("Users").child(userId).child("money").setValue(Cat.getMoney());
                                databaseReference.child("Users").child(userId).child("hunger").setValue(Cat.getHunger());
                                databaseReference.child("Users").child(userId).child("happy").setValue(Cat.getHappy());
                                Toast.makeText(food.this, "Purchase Complete", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
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
        });

        doughnut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(food.this);
                builder.setMessage("Are you sure you want to buy this?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Cat.addMoney(-10);
                                Cat.addHappy(5);
                                Cat.addHunger(10);
                                databaseReference.child("Users").child(userId).child("money").setValue(Cat.getMoney());
                                databaseReference.child("Users").child(userId).child("hunger").setValue(Cat.getHunger());
                                databaseReference.child("Users").child(userId).child("happy").setValue(Cat.getHappy());
                                Toast.makeText(food.this, "Purchase Complete", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
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
        });

        iceCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(food.this);
                builder.setMessage("Are you sure you want to buy this?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Cat.addMoney(-5);
                                Cat.addHappy(10);
                                Cat.addHunger(5);
                                databaseReference.child("Users").child(userId).child("money").setValue(Cat.getMoney());
                                databaseReference.child("Users").child(userId).child("hunger").setValue(Cat.getHunger());
                                databaseReference.child("Users").child(userId).child("happy").setValue(Cat.getHappy());
                                Toast.makeText(food.this, "Purchase Complete", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
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
        });

        fish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(food.this);
                builder.setMessage("Are you sure you want to buy this?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Cat.addMoney(-25);
                                Cat.addHappy(15);
                                Cat.addHunger(15);
                                databaseReference.child("Users").child(userId).child("money").setValue(Cat.getMoney());
                                databaseReference.child("Users").child(userId).child("hunger").setValue(Cat.getHunger());
                                databaseReference.child("Users").child(userId).child("happy").setValue(Cat.getHappy());
                                Toast.makeText(food.this, "Purchase Complete", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
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
        });


        milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(food.this);
                builder.setMessage("Are you sure you want to buy this?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Cat.addMoney(-10);
                                Cat.addHappy(10);
                                Cat.addHunger(5);
                                databaseReference.child("Users").child(userId).child("money").setValue(Cat.getMoney());
                                databaseReference.child("Users").child(userId).child("hunger").setValue(Cat.getHunger());
                                databaseReference.child("Users").child(userId).child("happy").setValue(Cat.getHappy());
                                Toast.makeText(food.this, "Purchase Complete", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
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
        });

        spaghetti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(food.this);
                builder.setMessage("Are you sure you want to buy this?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Cat.addMoney(-15);
                                Cat.addHappy(10);
                                Cat.addHunger(10);
                                databaseReference.child("Users").child(userId).child("money").setValue(Cat.getMoney());
                                databaseReference.child("Users").child(userId).child("hunger").setValue(Cat.getHunger());
                                databaseReference.child("Users").child(userId).child("happy").setValue(Cat.getHappy());
                                Toast.makeText(food.this, "Purchase Complete", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
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
        });

        chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(food.this);
                builder.setMessage("Are you sure you want to buy this?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Cat.addMoney(-20);
                                Cat.addHappy(10);
                                Cat.addHunger(15);
                                databaseReference.child("Users").child(userId).child("money").setValue(Cat.getMoney());
                                databaseReference.child("Users").child(userId).child("hunger").setValue(Cat.getHunger());
                                databaseReference.child("Users").child(userId).child("happy").setValue(Cat.getHappy());
                                Toast.makeText(food.this, "Purchase Complete", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
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
        });

        pizza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(food.this);
                builder.setMessage("Are you sure you want to buy this?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Cat.addMoney(-15);
                                Cat.addHappy(5);
                                Cat.addHunger(15);
                                databaseReference.child("Users").child(userId).child("money").setValue(Cat.getMoney());
                                databaseReference.child("Users").child(userId).child("hunger").setValue(Cat.getHunger());
                                databaseReference.child("Users").child(userId).child("happy").setValue(Cat.getHappy());
                                Toast.makeText(food.this, "Purchase Complete", Toast.LENGTH_SHORT).show();
                                dialog.cancel();
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
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        for(DataSnapshot ds:dataSnapshot.getChildren()){
            Cat=new cat();
            Cat.setMoney(ds.child(userId).getValue(cat.class).getMoney());
            Cat.setHunger(ds.child(userId).getValue(cat.class).getHunger());
            Cat.setHappy(ds.child(userId).getValue(cat.class).getHappy());
        }
    }

    public void onBackPressed() {
        Intent chg=new Intent(food.this,kitchen.class);
        startActivity(chg);
    }
}
