package com.example.game1;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class ProgressActivity extends AppCompatActivity {

    TextView text_back;

    int dataSize = 0,dataSize1 = 0,dataSize2 = 0;
    InputStream inputStream1,inputStream2;
    ArrayList<Float> acc_x_vals = new ArrayList<Float>();
    ArrayList<Float> acc_y_vals = new ArrayList<Float>();
    private LineGraphSeries<DataPoint> x_series, y_series;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress);

        getSupportActionBar().hide();

        text_back = findViewById(R.id.back_text);
        text_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        try {
            String str,str1,data1,data2,temp="extra";
            try {
                inputStream1 = openFileInput("memScore.txt");
                inputStream2 = openFileInput("speedScore.txt");
            } catch (FileNotFoundException e) {
                Toast.makeText(getApplicationContext(),"File not found",Toast.LENGTH_SHORT).show();
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream1));
            BufferedReader reader1 = new BufferedReader(new InputStreamReader(inputStream2));
            if (inputStream1!=null) {
                try {
                    while ((str = reader.readLine()) != null) {
                        data1 = str;
                        //temp = temp + data1;

                        acc_x_vals.add(Float.parseFloat(data1));
                        dataSize1 = dataSize1 + 1;
                    }
                    while ((str1 = reader1.readLine()) != null) {
                        data2 = str1;
                        //temp = temp + data2;

                        acc_y_vals.add(Float.parseFloat(data2));
                        dataSize2 = dataSize2 + 1;
                    }
                    if(dataSize1 > dataSize2){
                        dataSize = dataSize2;
                    }else if (dataSize1 < dataSize2){
                        dataSize = dataSize1;
                    }else{
                        dataSize = dataSize1;
                    }
                    //Toast.makeText(getApplicationContext(),temp,Toast.LENGTH_SHORT).show();
                    float x = 0, y = 0;
                    int datapoints = dataSize;
                    GraphView graph = (GraphView) findViewById(R.id.graph);
                    graph.setPadding(10, 10, 10, 10);
                    GridLabelRenderer gridLabelRenderer = graph.getGridLabelRenderer();
                    gridLabelRenderer.setGridColor(Color.WHITE);
                    gridLabelRenderer.setHorizontalLabelsColor(Color.WHITE);
                    gridLabelRenderer.setVerticalLabelsColor(Color.WHITE);


                    x_series = new LineGraphSeries<>();
                    y_series = new LineGraphSeries<>();

                    for (int i = 0; i < datapoints; i++) {
                        x = acc_x_vals.get(i);
                        y = acc_y_vals.get(i);
                        Toast.makeText(getApplicationContext(),String.valueOf(x),Toast.LENGTH_LONG).show();
                        x_series.appendData(new DataPoint(i, x), true, datapoints);
                        y_series.appendData(new DataPoint(i, y), true, datapoints);
                    }

                    Paint x_paint = new Paint();
                    Paint y_paint = new Paint();
                    x_paint.setStyle(Paint.Style.STROKE);
                    x_paint.setStrokeWidth(6);
                    x_paint.setColor(Color.GREEN);
                    y_paint.setStyle(Paint.Style.STROKE);
                    y_paint.setStrokeWidth(6);
                    y_paint.setColor(Color.YELLOW);
                    x_series.setCustomPaint(x_paint);
                    y_series.setCustomPaint(y_paint);

                    graph.getLegendRenderer().setVisible(true);
                    graph.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);

                    x_series.setTitle("Memory Enhancer Game");
                    x_series.setColor(Color.GREEN);
                    y_series.setTitle("Speed Enhancer Game");
                    y_series.setColor(Color.YELLOW);

                    graph.addSeries(x_series);
                    graph.addSeries(y_series);
                } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "not working : "+e, Toast.LENGTH_SHORT).show();
                }
            }
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(), "not working : "+e, Toast.LENGTH_SHORT).show();
        }
    }

    public void onBackPressed() {
        finish();
    }
}