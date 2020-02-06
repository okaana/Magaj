package com.example.game1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    TextView mem_game_text,speed_game_text,back_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        getSupportActionBar().hide();

        mem_game_text = findViewById(R.id.mem_game_text);
        speed_game_text = findViewById(R.id.speed_game_text);
        back_text = findViewById(R.id.back_text);

        back_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mem_game_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent memGame = new Intent(getApplicationContext(),MemActivity.class);
                startActivity(memGame);
            }
        });
        speed_game_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent speedGame = new Intent(getApplicationContext(),SpeedActivity.class);
                startActivity(speedGame);
            }
        });
    }
}
