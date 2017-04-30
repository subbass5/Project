package com.example.windowsnt.project;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MonthActivity extends Activity {
    private Bundle bn;
    private Button btnbacktomain;
    private TextView txtHeader,txtUnit,txtPerUnit,txtService,txtmixPower,txtFt,txtVat,txtTotalPrice;
    Calendar cn;
    private float unitInputs;
    private String monthofYear;
    private DatabaseReference myRef,myRef2,getMyRefmonth;
    boolean chkPut;
    float monthData;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_month);
        setTitle("ค่าไฟฟ้าประจำเดือน");

        bn = getIntent().getExtras();
        txtUnit = (TextView) findViewById(R.id.txtunit);
        txtPerUnit = (TextView) findViewById(R.id.txtperunit);
        txtService = (TextView) findViewById(R.id.txtService);
        txtmixPower = (TextView) findViewById(R.id.txtmixPower);
        txtFt = (TextView) findViewById(R.id.txtft);
        txtVat = (TextView) findViewById(R.id.txtVat);
        txtTotalPrice = (TextView) findViewById(R.id.txttotal);
        btnbacktomain = (Button) findViewById(R.id.btnbacktomain);
        txtHeader = (TextView) findViewById(R.id.txtHaed);
        unitInputs = bn.getFloat("unitTotal");

        myRef = FirebaseDatabase.getInstance().getReference("Electricity back");
        myRef2 = FirebaseDatabase.getInstance().getReference("checkState");

        final CalculatorUnit calUnit = new CalculatorUnit();
        calUnit.Calculator(unitInputs);
        final Date date = new Date();
        final SimpleDateFormat ft =
                new SimpleDateFormat("เดือน M ปี yyyy");
        final SimpleDateFormat ft2 =
                new SimpleDateFormat("d");
//        Toast.makeText(this, "" + ft2.format(date), Toast.LENGTH_SHORT).show();

        Log.d("Current Date : ", "" + ft.format(date));
        monthofYear = ft.format(date);
        txtHeader.setText(monthofYear);
        txtUnit.setText("" + unitInputs);
        if (unitInputs < 150) {

            txtmixPower.setText("" + calUnit.getBase());
            txtPerUnit.setText("" + calUnit.getPower());
            txtFt.setText("" + calUnit.getFT());
            txtService.setText("" + calUnit.getService());
            txtVat.setText("" + calUnit.getVat());
            txtTotalPrice.setText("" + calUnit.getSummary());
        } else {

            txtmixPower.setText("" + calUnit.getBase150());
            txtPerUnit.setText("" + calUnit.getPower150());
            txtFt.setText("" + calUnit.getFT());
            txtService.setText("" + calUnit.getService150());
            txtVat.setText("" + calUnit.getVAT150());
            txtTotalPrice.setText("" + calUnit.getSummary150());
        }

            btnbacktomain.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
        myRef2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                chkPut = dataSnapshot.getValue(Boolean.class);
                Log.e("Status",""+chkPut);
                if (unitInputs < 150) {
                    if (ft2.format(date).equals("18")) {
                        if(chkPut==true){
                            HashMap<String, Object> value = new HashMap<>();
                            value.put("date", ft.format(date));
                            value.put("unit", unitInputs);
                            value.put("price", calUnit.getSummary());
                            myRef.push().setValue(value);
                            myRef2.setValue(false);

                        }


                    }else {
                        myRef2.setValue(true);
                    }
                    txtmixPower.setText("" + calUnit.getBase());
                    txtPerUnit.setText("" + calUnit.getPower());
                    txtFt.setText("" + calUnit.getFT());
                    txtService.setText("" + calUnit.getService());
                    txtVat.setText("" + calUnit.getVat());
                    txtTotalPrice.setText("" + calUnit.getSummary());
                } else {
                    if (ft2.format(date).equals("18")) {
                        if (chkPut == true) {
                            HashMap<String, Object> value = new HashMap<>();
                            value.put("date", ft.format(date));
                            value.put("unit", unitInputs);
                            value.put("price", calUnit.getSummary150());
                            myRef.push().setValue(value);
                            myRef2.setValue(false);
                        }


                    } else {
                        myRef2.setValue(true);
                    }
                }
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
