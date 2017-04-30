package com.example.windowsnt.project;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.numetriclabz.numandroidcharts.ChartData;
import com.numetriclabz.numandroidcharts.LineChart;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.numetriclabz.numandroidcharts.ChartData;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.numetriclabz.numandroidcharts.ChartData.LineChart;


/**
 * Created by windows7 on 12/17/2016.
 */

public class Class1activity extends Activity {
    int n=24;
    private Bundle bd;
    LineChart lineChart;
    List<ChartData> values = new ArrayList<>();


    private  DatabaseReference mDatabaseRef;
    private DatabaseReference log;
    private boolean status=false;
    private Button buttonRe,btngoMain1;
    private String Income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghaph);
        bd =getIntent().getExtras();
        Income = bd.getString("findData");
        setTitle("สถิติการใช้ไฟฟ้ารายวัน");

    }

    @Override
    protected void onResume() {
        super.onResume();
        getQuery();
        buttonRe = (Button) findViewById(R.id.btnRe1);
        btngoMain1 = (Button) findViewById(R.id.btnGoMain1);
        lineChart = (LineChart) findViewById(R.id.linechart);
        if (Income.equals("NO")){
            lineChart.setWillNotDraw(true);
        }else if(Income.equals("YES")){
            lineChart.setWillNotDraw(false);
        }

        buttonRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  re = new Intent(Class1activity.this,Class1activity.class);
                re.putExtra("findData","YES");
                startActivity(re);
                finish();
            }
        });
        btngoMain1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        lineChart.setData(values);
        lineChart.setCircleSize(15f);
        List<ChartData> trendlines = new ArrayList<>();
        trendlines.add(new ChartData(1000f, 3000f,"High","Very High"));
        lineChart.setTrendlines(trendlines);
        lineChart.setDescription("Y จำนวน Watt , X เวลา");
        onRestart();

    }
    private void getQuery(){
        final int c = 1;
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        log = mDatabaseRef.child("logHr");

        Query query = log.limitToLast(n);
        Log.d("Query :","I'm  here!");
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> value = (Map<String,Object>) dataSnapshot.getValue();
                String times = value.get("time").toString();
                times = times.substring(9,17);
                //  Log.i("DATA",value.toString());
                values.add(new ChartData(Float.parseFloat(value.get("All").toString()), ""+times));
//                System.out.println("Doing!");
                status = true;
//                Log.e("Status",""+status);


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}