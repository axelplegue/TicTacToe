package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    private TextView player1;
    private TextView player2;
    ImageView a1, a2, a3, b1, b2, b3, c1, c2, c3;
    private int turn = 1;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference usersReference = mDatabase.getReference().child("Users");

        player1 = findViewById(R.id.player1);
        player2 = findViewById(R.id.player2);
        a1 = findViewById(R.id.A1);
        a2 = findViewById(R.id.A2);
        a3 = findViewById(R.id.A3);
        b1 = findViewById(R.id.B1);
        b2 = findViewById(R.id.B2);
        b3 = findViewById(R.id.B3);
        c1 = findViewById(R.id.C1);
        c2 = findViewById(R.id.C2);
        c3 = findViewById(R.id.C3);

        ValueEventListener usersListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                        String userName = userSnapshot.getValue(String.class);
                        if (userSnapshot.getKey().equals("User1")) {
                            player1.setText(userName);
                        } else if (userSnapshot.getKey().equals("User2")) {
                            player2.setText(userName);
                        }
                    }
                } else {
                    // Manejar el caso en el que no existen usuarios
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de lectura de Firebase si es necesario
            }
        };


        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == 1) {
                    turn = 2;
                    a1.setImageResource(R.drawable.close);
                } else {
                    turn = 1;
                    a1.setImageResource(R.drawable.rec);
                }
            }
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == 1) {
                    turn = 2;
                    a2.setImageResource(R.drawable.close);
                } else {
                    turn = 1;
                    a2.setImageResource(R.drawable.rec);
                }
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == 1) {
                    turn = 2;
                    a3.setImageResource(R.drawable.close);
                } else {
                    turn = 1;
                    a3.setImageResource(R.drawable.rec);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == 1) {
                    turn = 2;
                    b1.setImageResource(R.drawable.close);
                } else {
                    turn = 1;
                    b1.setImageResource(R.drawable.rec);
                }
            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == 1) {
                    turn = 2;
                    b2.setImageResource(R.drawable.close);
                } else {
                    turn = 1;
                    b2.setImageResource(R.drawable.rec);
                }
            }
        });


        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == 1) {
                    turn = 2;
                    b3.setImageResource(R.drawable.close);
                } else {
                    turn = 1;
                    b3.setImageResource(R.drawable.rec);
                }
            }
        });


        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == 1) {
                    turn = 2;
                    c1.setImageResource(R.drawable.close);
                } else {
                    turn = 1;
                    c1.setImageResource(R.drawable.rec);
                }
            }
        });


        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == 1) {
                    turn = 2;
                    c2.setImageResource(R.drawable.close);
                } else {
                    turn = 1;
                    c2.setImageResource(R.drawable.rec);
                }
            }
        });


        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn == 1) {
                    turn = 2;
                    c3.setImageResource(R.drawable.close);
                } else {
                    turn = 1;
                    c3.setImageResource(R.drawable.rec);
                }
            }
        });
        usersReference.addListenerForSingleValueEvent(usersListener);
    }

    }


