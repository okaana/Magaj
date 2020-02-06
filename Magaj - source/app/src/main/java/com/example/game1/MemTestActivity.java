package com.example.game1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemTestActivity extends AppCompatActivity {

    Button b_1,b_2,b_3,b_4,b_5,b_6,b_7,b_8,b_9,b_10,b_11,b_12;
    int bck_count=0,bck_count1=0;
    TextView t,new_game_text,back_text;

    List<Integer> buttons;
    int curLevel,curGuess;
    int score=0,count=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mem1);

        getSupportActionBar().hide();

        t = findViewById(R.id.score_text);
        back_text = findViewById(R.id.text_back);
        new_game_text = findViewById(R.id.new_game_text);

        b_1 = findViewById(R.id.button_1);
        b_2 = findViewById(R.id.button_2);
        b_3 = findViewById(R.id.button_3);
        b_4 = findViewById(R.id.button_4);
        b_5 = findViewById(R.id.button_5);
        b_6 = findViewById(R.id.button_6);
        b_7 = findViewById(R.id.button_7);
        b_8 = findViewById(R.id.button_8);
        b_9 = findViewById(R.id.button_9);
        b_10 = findViewById(R.id.button_10);
        b_11 = findViewById(R.id.button_11);
        b_12 = findViewById(R.id.button_12);

        b_1.setTag(1);
        b_2.setTag(2);
        b_3.setTag(3);
        b_4.setTag(4);
        b_5.setTag(5);
        b_6.setTag(6);
        b_7.setTag(7);
        b_8.setTag(8);
        b_9.setTag(9);
        b_10.setTag(10);
        b_11.setTag(11);
        b_12.setTag(12);

        disableButtons();

        buttons = new ArrayList<>();
        buttons.add(1);
        buttons.add(2);
        buttons.add(3);
        buttons.add(4);
        buttons.add(5);
        buttons.add(6);
        buttons.add(7);
        buttons.add(8);
        buttons.add(9);
        buttons.add(10);
        buttons.add(11);
        buttons.add(12);


        //  t.setText("Score"+Integer.toString(score));
        back_text.setVisibility(View.INVISIBLE);

        new_game_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 0)
                {
                    new_game_text.setVisibility(View.INVISIBLE);
                    curGuess = 0;
                    curLevel = 1;
                    generateButtons(curLevel);
                    count = 1;
                }
                else if (count == 1){
                    Intent i = new Intent(getApplicationContext(),SpeedTestActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });

        b_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button) v).setText("O");
            }
        });

        b_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button) v).setText("O");
            }
        });
        b_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button) v).setText("O");
            }
        });
        b_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button) v).setText("O");
            }
        });
        b_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button) v).setText("O");
            }
        });
        b_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button) v).setText("O");
            }
        });
        b_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button) v).setText("O");
            }
        });
        b_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button) v).setText("O");
            }
        });
        b_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button) v).setText("O");
            }
        });
        b_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button) v).setText("O");
            }
        });
        b_11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button) v).setText("O");
            }
        });
        b_12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonLogic(v);
                ((Button) v).setText("O");
            }
        });

    }

    private void buttonLogic(View view){
        List<Integer> tempList = new ArrayList<>();
        for(int i=0;i<curLevel;i++){
            tempList.add(buttons.get(i));
        }

        if (tempList.contains(view.getTag())){
            curGuess++;
            checkWin();
        }
        else{
            lostGame();
        }
    }

    private void lostGame(){
        Toast.makeText(this,"Its ok We are just starting!",Toast.LENGTH_SHORT).show();
        disableButtons();

        new_game_text.setVisibility(View.VISIBLE);
        new_game_text.setText("Next test");
        try {
            String string = String.valueOf(score);
            FileOutputStream fOut = openFileOutput("memScore.txt", MODE_APPEND);
            fOut.write(string.getBytes());
            fOut.close();
            Toast.makeText(getApplicationContext(),"Score Entered ",Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"Error entering score : "+e,Toast.LENGTH_SHORT).show();
        }
    }

    private void checkWin(){
        if(curGuess == curLevel){
            score+=10;
            t.setText("Score:" + Integer.toString(score));

            disableButtons();
            if (curLevel == 12) {
                Toast.makeText(this,"You are really good at this!",Toast.LENGTH_SHORT).show();

                new_game_text.setVisibility(View.VISIBLE);
                new_game_text.setText("Next test");
                try {
                    String string = String.valueOf(score);
                    FileOutputStream fOut = openFileOutput("memScore.txt", MODE_APPEND);
                    fOut.write(string.getBytes());
                    fOut.close();
                    Toast.makeText(getApplicationContext(),"Score Entered ",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Error entering score : "+e,Toast.LENGTH_SHORT).show();
                }
                t.setText(Integer.toString(curLevel));
            }
            else {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        curGuess = 0;
                        curLevel++;
                        generateButtons(curLevel);

                    }
                },1000);
            }
        }
    }

    private void generateButtons(int number){
        b_1.setText("");
        b_2.setText("");
        b_3.setText("");
        b_4.setText("");
        b_5.setText("");
        b_6.setText("");
        b_7.setText("");
        b_8.setText("");
        b_9.setText("");
        b_10.setText("");
        b_11.setText("");
        b_12.setText("");

        Collections.shuffle(buttons);

        for(int i=0;i<number;i++){
            showButton(buttons.get(i));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                b_1.setText("");
                b_2.setText("");
                b_3.setText("");
                b_4.setText("");
                b_5.setText("");
                b_6.setText("");
                b_7.setText("");
                b_8.setText("");
                b_9.setText("");
                b_10.setText("");
                b_11.setText("");
                b_12.setText("");

                enableButtons();

            }
        },1000);
    }
    private void showButton(int number){
        switch(number){
            case 1:
                b_1.setText("O");
                break;
            case 2:
                b_2.setText("O");
                break;
            case 3:
                b_3.setText("O");
                break;
            case 4:
                b_4.setText("O");
                break;
            case 5:
                b_5.setText("O");
                break;
            case 6:
                b_6.setText("O");
                break;
            case 7:
                b_7.setText("O");
                break;
            case 8:
                b_8.setText("O");
                break;
            case 9:
                b_9.setText("O");
                break;
            case 10:
                b_10.setText("O");
                break;
            case 11:
                b_11.setText("O");
                break;
            case 12:
                b_12.setText("O");
                break;
        }

    }
    private void enableButtons(){
        b_1.setEnabled(true);
        b_2.setEnabled(true);
        b_3.setEnabled(true);
        b_4.setEnabled(true);
        b_5.setEnabled(true);
        b_6.setEnabled(true);
        b_7.setEnabled(true);
        b_8.setEnabled(true);
        b_9.setEnabled(true);
        b_10.setEnabled(true);
        b_11.setEnabled(true);
        b_12.setEnabled(true);
    }

    private void disableButtons(){
        b_1.setEnabled(false);
        b_2.setEnabled(false);
        b_3.setEnabled(false);
        b_4.setEnabled(false);
        b_5.setEnabled(false);
        b_6.setEnabled(false);
        b_7.setEnabled(false);
        b_8.setEnabled(false);
        b_9.setEnabled(false);
        b_10.setEnabled(false);
        b_11.setEnabled(false);
        b_12.setEnabled(false);
    }

    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Please complete the test",Toast.LENGTH_SHORT).show();
    }
}