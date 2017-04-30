package com.example.windowsnt.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.numetriclabz.numandroidcharts.ChartData;
import com.numetriclabz.numandroidcharts.LineChart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;



public class Viewgraph2 extends Activity {
    int n=30;
    private Bundle bd;
    LineChart lineChart;
    List<ChartData> values = new ArrayList<>();
    private  DatabaseReference mDatabaseRef;
    private DatabaseReference log;
    private boolean status=false;
    private Button buttonRe,btnGomain2;
    private String Income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghaph2);
        bd = getIntent().getExtras();
        Income = bd.getString("findData");
        setTitle("สถิติการใช้ไฟฟ้ารายเดือน");

    }

    @Override
    protected void onResume() {
        super.onResume();
        getQuery();
        buttonRe = (Button) findViewById(R.id.btnRe2);
        btnGomain2 = (Button) findViewById(R.id.btnGoMain2);
        lineChart = (LineChart) findViewById(R.id.linechart2);
        if (Income.equals("NO")){
            lineChart.setWillNotDraw(true);
        }else if(Income.equals("YES")){
            lineChart.setWillNotDraw(false);
        }

        buttonRe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  re = new Intent(Viewgraph2.this,Viewgraph2.class);
                re.putExtra("findData","YES");
                startActivity(re);
                finish();
            }
        });
        btnGomain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lineChart.setData(values);
        lineChart.setCircleSize(15f);
        lineChart.setWillNotCacheDrawing(true);
        List<ChartData> trendlines = new ArrayList<>();
        trendlines.add(new ChartData(1000f, 3000f,"High","Very High"));
        lineChart.setTrendlines(trendlines);

        lineChart.setDescription("Y หน่วย , X เวลา");
        onRestart();

    }
    private void getQuery(){
        mDatabaseRef = FirebaseDatabase.getInstance().getReference();
        log = mDatabaseRef.child("logDate");

        Query query = log.limitToLast(n);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String, Object> value = (Map<String,Object>) dataSnapshot.getValue();
                String times = value.get("time").toString();
                 Log.i("DATA",value.toString());
                values.add(new ChartData(Float.parseFloat(value.get("unit").toString()), ""+times.substring(0,8)));
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
