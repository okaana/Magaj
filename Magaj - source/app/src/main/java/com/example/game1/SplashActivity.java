package com.example.game1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import java.io.FileInputStream;

public class SplashActivity extends AppCompatActivity {

    Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        try {
            FileInputStream fin = openFileInput("initialdata.txt");
            int c;
            String temp="";
            while( (c = fin.read()) != -1){
                temp = temp + Character.toString((char)c);
            }
            //Toast.makeText(getBaseContext(),temp,Toast.LENGTH_SHORT).show();
            String data[] = temp.split("\n");
            String data1[] = data[1].split(":");
            int ager = Integer.valueOf(data1[1]);
            DataActivity.user_age = ager;
            if(temp.equals("")){
                i = new Intent(getApplicationContext(),DataActivity.class);
            }else {
                i = new Intent(getApplicationContext(),MainActivity.class);
            }
        }
        catch(Exception e){
            i = new Intent(getApplicationContext(),DataActivity.class);
        }

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
