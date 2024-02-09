package com.example.tictactoe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private TextView player1;
    private TextView player2;
    private ImageView a1, a2, a3, b1, b2, b3, c1, c2, c3;
    private int turn;
    private int user;
    private String player1Name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
        DatabaseReference usersReference = mDatabase.getReference().child("Users");
        DatabaseReference turnsReference = mDatabase.getReference().child("Turn");

        Intent getIntent = getIntent();
        player1Name = getIntent.getStringExtra("player1");

        // Listener para obtener el turno
        turnsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    turn = dataSnapshot.getValue(Integer.class);
                } else {
                    turn = 1; // Por defecto, asignamos el turno 1 si no hay datos en la base de datos
                    turnsReference.setValue(turn);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de lectura de Firebase si es necesario
                Toast.makeText(MainActivity.this, "Error al obtener el turno", Toast.LENGTH_SHORT).show();
            }
        });

        // Listener para obtener el usuario y asignar turnos
        usersReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (Objects.equals(dataSnapshot.child("User1").getValue(String.class), player1Name)) {
                    user = 1;
                    player1.setText(player1Name);
                } else {
                    user = 2;
                    player2.setText(dataSnapshot.child("User1").getValue(String.class));
                }

                if (user == turn) {
                    assignClickListeners();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de lectura de Firebase si es necesario
                Toast.makeText(MainActivity.this, "Error al obtener el usuario", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void assignClickListeners() {
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTurnLogic(a1);
            }
        });

        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTurnLogic(a2);
            }
        });

        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTurnLogic(a3);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTurnLogic(b1);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTurnLogic(b2);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTurnLogic(b3);
            }
        });

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTurnLogic(c1);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTurnLogic(c2);
            }
        });

        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTurnLogic(c3);
            }
        });
    }

    private void handleTurnLogic(ImageView imageView) {
        if (user == 1) {
            imageView.setImageResource(R.drawable.rec);
        } else {
            imageView.setImageResource(R.drawable.close);
        }

        // Cambiar el turno y actualizar en la base de datos
        turn = (turn == 1) ? 2 : 1;
        FirebaseDatabase.getInstance().getReference().child("Turn").setValue(turn);

        // Desactivar los clics en la imagen después de hacer la jugada
        imageView.setClickable(false);
    }
}
