package com.example.game1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import java.io.FileOutputStream;
import java.util.Calendar;

public class DataActivity extends AppCompatActivity {

    EditText edit_name,edit_age,edit_dob;
    RadioButton rmale,rfemale;
    RadioGroup rsex;
    TextView text_test;
    String name,age,dob,sex;

    public static int user_age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        getSupportActionBar().setTitle("Please Enter Your details and take a Test");

        edit_name = findViewById(R.id.edit_name);
        edit_age = findViewById(R.id.edit_age);
        edit_dob = findViewById(R.id.edit_dob);

        rmale = findViewById(R.id.male_radioButton);
        rfemale = findViewById(R.id.female_radioButton2);
        rsex = findViewById(R.id.age_radio);

        text_test = findViewById(R.id.test_text);

        text_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edit_name.getText().toString();
                age = edit_age.getText().toString();
                dob = edit_dob.getText().toString();

                int year = Calendar.getInstance().get(Calendar.YEAR);

                if (name.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter the name",Toast.LENGTH_SHORT).show();
                }else if(age.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter the age",Toast.LENGTH_SHORT).show();
                }else if (dob.equals("")){
                    Toast.makeText(getApplicationContext(),"Please enter the Date of Birth (D.O.B.)",Toast.LENGTH_SHORT).show();
                }else if (rsex.getCheckedRadioButtonId() == -1){
                    Toast.makeText(getApplicationContext(), "Please select your sex", Toast.LENGTH_SHORT).show();
                }else{
                    String temp[] = dob.split("/");
                    int month = Integer.parseInt(temp[0]);
                    int yer = Integer.parseInt(temp[1]);
                    Toast.makeText(getApplicationContext(),"month : "+year,Toast.LENGTH_SHORT).show();
                    if(month > 12){
                        Toast.makeText(getApplicationContext(),"Please Enter valid month",Toast.LENGTH_SHORT).show();
                    }else if (yer < 1900 || yer > year ){
                        Toast.makeText(getApplicationContext(),"Please Enter a valid year",Toast.LENGTH_SHORT).show();
                    }else{
                        if(rmale.isChecked()){
                            sex = "Male";
                        }else if(rfemale.isChecked()){
                            sex = "Female";
                        }

                        createfiles();
                        Writedata();
                    }
                }
            }
        });
    }
    public void createfiles(){
        try {
            String string = "0";
            FileOutputStream fOut = openFileOutput("memScore.txt",MODE_APPEND);
            fOut.write(string.getBytes());
            fOut.close();
            FileOutputStream fOut1 = openFileOutput("speedScore.txt",MODE_APPEND);
            fOut1.write(string.getBytes());
            Toast.makeText(getApplicationContext(),"Files Created ",Toast.LENGTH_SHORT).show();
            fOut1.close();
        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"error creating files : "+e,Toast.LENGTH_SHORT).show();
        }


        //finish();
    }
    public void Writedata(){
        try {
            String string = "Name:"+name+"\nAge:"+age+"\nDob:"+dob+"\nSex:"+sex+"\n";
            FileOutputStream fOut = openFileOutput("initialdata.txt",MODE_APPEND);
            fOut.write(string.getBytes());
            fOut.close();
            Toast.makeText(getBaseContext(),"Data saved",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,MemTestActivity.class);
            startActivity(i);
            finish();
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            Toast.makeText(getApplicationContext(),"error : "+e,Toast.LENGTH_SHORT).show();
        }
    }
    public void onBackPressed(){
        Toast.makeText(getApplicationContext(),"Please complete the test",Toast.LENGTH_SHORT).show();
    }
}
