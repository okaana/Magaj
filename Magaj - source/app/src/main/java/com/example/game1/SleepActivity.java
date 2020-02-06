package com.example.game1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SleepActivity extends AppCompatActivity {

    EditText edit_slept,edit_gotup;
    TextView text_cal,text_back,text_result;
    TimePickerDialog picker,picker1;
    int slept_hr=0,slept_min=0,gotup_hr=0,gotup_min=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        getSupportActionBar().hide();

        edit_gotup = findViewById(R.id.edit_gotup);
        edit_slept = findViewById(R.id.edit_slept);

        text_cal = findViewById(R.id.text_calculate);
        text_result = findViewById(R.id.text_result);
        text_back = findViewById(R.id.back_text);

        text_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        text_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String time1 = slept_hr+":"+slept_min+":00";
                String time2 = gotup_hr+":"+gotup_min+":00";

                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                try {
                    Date date1 = format.parse(time1);
                    Date date2 = format.parse(time2);
                    long difference = date2.getTime() - date1.getTime();
                    difference = difference/60000;
                    Toast.makeText(getApplicationContext(),"slept time : "+difference,Toast.LENGTH_SHORT).show();
                    if (difference == 0.00){
                        Toast.makeText(getApplicationContext(),"Please enter valid time",Toast.LENGTH_SHORT).show();
                    }else if (DataActivity.user_age > 18){
                        if(difference > 480) {
                            difference = difference/60;
                            text_result.setText("Congradulations, You had a very good sleep time!\nYou slept for " + difference +" hours");
                        }else{
                            difference = difference/60;
                            text_result.setText("Ohhh!!,Looks like you need more sleep time!\nYou slept for only " + difference + " hours");
                        }
                    }else if (DataActivity.user_age > 64 && DataActivity.user_age < 17){
                        if(difference > 420){
                            difference = difference/60;
                            text_result.setText("Congradulations, You had a very good sleep time!\nYou slept for " + difference +" hours");
                        }else {
                            difference = difference/60;
                            text_result.setText("Ohhh!!,Looks like you need more sleep time!\nYou slept for only " + difference + " hours");
                        }
                    }else {
                        if(difference > 420){
                            difference = difference/60;
                            text_result.setText("Congradulations, You had a very good sleep time!\nYou slept for " + difference +" hours");
                        }else {
                            difference = difference/60;
                            text_result.setText("Ohhh!!,Looks like you need more sleep time!\nYou slept for only " + difference + " hours");
                        }
                    }
                }catch(Exception e) {
                }
            }
        });

        edit_slept.setInputType(InputType.TYPE_NULL);
        edit_slept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker = new TimePickerDialog(SleepActivity.this,R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                edit_slept.setText(sHour + ":" + sMinute);
                                slept_hr = sHour;
                                slept_min = sMinute;
                            }
                        }, hour, minutes, true);

                picker.show();
            }
        });

        edit_gotup.setInputType(InputType.TYPE_NULL);
        edit_gotup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                picker1 = new TimePickerDialog(SleepActivity.this,R.style.DialogTheme, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                        edit_gotup.setText(sHour + ":" + sMinute);
                        gotup_hr = sHour;
                        gotup_min = sMinute;
                    }
                }, hour, minutes, true);

                picker1.show();
            }
        });
    }

    public void onBackPressed() {
        finish();
    }
}
