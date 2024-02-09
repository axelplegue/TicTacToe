package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddPlayers extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_players);
        final EditText player1 = findViewById(R.id.player1);

        final Button startGame = findViewById(R.id.startGame);

        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String player1Name = player1.getText().toString();
                if (player1Name.isEmpty() ) {
                    player1.setError("Please enter a name");
                } else {
                    addUser(player1Name);
                    Intent intent = new Intent(AddPlayers.this, MainActivity.class);
                    intent.putExtra("player1", player1Name);
                    startActivity(intent);
                }
            }
        });
    }
    public static void addUser(String player1Name) {
        try {
            FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();
            if(mDatabase.getReference().child("Users").child("User1").equals("")){
                mDatabase.getReference().child("Users").child("User1").setValue(player1Name);
            }
            else if(mDatabase.getReference().child("Users").child("User2").equals("")){
                mDatabase.getReference().child("Users").child("User2").setValue(player1Name);
            }



        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}