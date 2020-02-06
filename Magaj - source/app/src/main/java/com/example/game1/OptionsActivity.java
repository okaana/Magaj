package com.example.game1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {

    TextView text_meds,text_food,text_back,text_sleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        getSupportActionBar().hide();

        text_meds = findViewById(R.id.meds_text);
        text_food = findViewById(R.id.food_text);
        text_sleep = findViewById(R.id.sleep_text);
        text_back = findViewById(R.id.back_text);

        text_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        text_meds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), ProgressActivity.class);
                startActivity(i);
            }
        });

        text_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),FoodActivity.class);
                startActivity(i);
            }
        });
        text_sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),SleepActivity.class);
                startActivity(i);
            }
        });
    }
    public void onBackPressed(){
        finish();
    }
}
