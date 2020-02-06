package com.example.game1;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileOutputStream;

public class SpeedTestActivity extends AppCompatActivity {

    Button go,a_0,a_1,a_2,a_3;
    TextView timer,score,question,bottom,back_text;
    ProgressBar pb;

    qgame g = new qgame();
    int secondsRemaining = 30;
    int bck_count=0,bck_count1=0,count=0;

    CountDownTimer ct = new CountDownTimer(30000,1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            secondsRemaining--;
            timer.setText(Integer.toString(secondsRemaining));
            pb.setProgress(30 - secondsRemaining);

        }

        @Override
        public void onFinish() {
            a_0.setEnabled(false);
            a_1.setEnabled(false);
            a_2.setEnabled(false);
            a_3.setEnabled(false);
            timer.setText("Times up!!");
            bottom.setText("Time Up!" + g.getNumberCorrect() + "/" + (g.getTotalQuestions()-1));

            final Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    go.setVisibility(View.VISIBLE);
                    go.setText("Main\nMenu");
                    try{
                        String string = String.valueOf(g.getNumberCorrect());
                        FileOutputStream fOut1 = openFileOutput("speedScore.txt",MODE_APPEND);
                        fOut1.write(string.getBytes());
                        fOut1.close();
                        Toast.makeText(getApplicationContext(),"Score Entered ",Toast.LENGTH_SHORT).show();
                    }catch (Exception e){
                        Toast.makeText(getApplicationContext(),"Error entering score : "+e,Toast.LENGTH_SHORT).show();
                    }

                }
            },4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speed);

        getSupportActionBar().hide();

        go = findViewById(R.id.go_button);
        a_0 = findViewById(R.id.b_ans0);
        a_1 = findViewById(R.id.b_ans1);
        a_2 = findViewById(R.id.b_ans2);
        a_3 = findViewById(R.id.b_ans3);

        score = findViewById(R.id.tv_score);
        timer = findViewById(R.id.tv_timer);
        bottom = findViewById(R.id.tv_bottom);
        question = findViewById(R.id.tv_question);
        back_text = findViewById(R.id.back_text);

        timer.setText("0 secs");
        question.setText("");
        bottom.setText("Press Go");
        score.setText("0pts");

        back_text.setVisibility(View.INVISIBLE);

        pb = findViewById(R.id.progressBar);

        View.OnClickListener goClickListener=  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count == 0) {
                    Button start = (Button) v;
                    start.setVisibility(View.INVISIBLE);

                    secondsRemaining = 30;
                    g = new qgame();

                    nextTurn();
                    ct.start();
                    count = 1 ;
                }else if (count == 1){
                    Intent i = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        };

        View.OnClickListener ansClickListener=  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button ansclick = (Button) v;

                int anwserSelected = Integer.parseInt(ansclick.getText().toString());
                g.checkAnswer(anwserSelected);
                score.setText( Integer.toString(g.getScore()));
                nextTurn();


            }
        };

        go.setOnClickListener(goClickListener);
        a_0.setOnClickListener(ansClickListener);
        a_1.setOnClickListener(ansClickListener);
        a_2.setOnClickListener(ansClickListener);
        a_3.setOnClickListener(ansClickListener);
    }

    private void nextTurn(){
        g.makeNewQuest();
        int [] answer = g.getCurrentQuestion().getAnswerArray();

        a_0.setText(Integer.toString(answer[0]));
        a_1.setText(Integer.toString(answer[1]));
        a_2.setText(Integer.toString(answer[2]));
        a_3.setText(Integer.toString(answer[3]));

        a_0.setEnabled(true);
        a_1.setEnabled(true);
        a_2.setEnabled(true);
        a_3.setEnabled(true);


        question.setText(g.getCurrentQuestion().getQuestionPhrase());
        bottom.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions()-1));
    }
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Please Finish the test",Toast.LENGTH_SHORT).show();
    }
}
