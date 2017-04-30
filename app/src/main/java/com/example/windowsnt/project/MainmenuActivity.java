package com.example.windowsnt.project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.preference.DialogPreference;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.content.DialogInterface.OnClickListener;
import com.beardedhen.androidbootstrap.TypefaceProvider;
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

import az.plainpie.PieView;
import az.plainpie.animation.PieAngleAnimation;

public class MainmenuActivity extends ActionBarActivity  {
    private FirebaseDatabase mDb;
    private int n=0,k=0;
    private DatabaseReference mRefClass1,mRefClass2,mRefClass3,mRefall,unitRef,backupUnit,select2,pushMonth;
    private TextView txall;
    private EditText etxtTest;
    private Button btnshow1,btngoMonth,btngoMonthed,btnSimmulator,btnGoWeb,btnGoList;
    private ProgressDialog con ;
    private Calendar myCalendar;
    private float unitsInput,united;
    private String simulatorUnit,dateChk,dateChk2;
    Notification.Builder notification;
    final int id = 11;
    Date date;
    SimpleDateFormat sf2;
    int notification1 = 1;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);
        getSupportActionBar().hide();
        TypefaceProvider.registerDefaultIconSets();
        con = new ProgressDialog(this);
        openDialog(n);
        setTitle("Main Menu");
        mDb = FirebaseDatabase.getInstance();
        mRefClass1 = mDb.getReference("001/value/Class1");
        mRefClass2 = mDb.getReference("001/value/Class2");
        mRefClass3 = mDb.getReference("001/value/Class3");
        mRefall = mDb.getReference("001/value/All");
        unitRef = mDb.getReference("001/value/unit");
        pushMonth = mDb.getReference("Electricity back");
        backupUnit = FirebaseDatabase.getInstance().getReference();
        select2 = backupUnit.child("Electricity back");
        txall =(TextView) findViewById(R.id.txtall);
        btnshow1 = (Button) findViewById(R.id.btnStatic);
        btngoMonth = (Button) findViewById(R.id.btngoMonth);
        btngoMonthed = (Button) findViewById(R.id.btngoMonthed);
        btnSimmulator = (Button) findViewById(R.id.btnSimmulator);
        btnGoWeb = (Button) findViewById(R.id.btnWebView);
        btnGoList = (Button) findViewById(R.id.btnList);
        btnGoWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goWeb = new Intent(MainmenuActivity.this,WebviewActivity.class);
                startActivity(goWeb);
            }
        });

        btngoMonth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMonth = new Intent(MainmenuActivity.this,MonthActivity.class);
                goMonth.putExtra("unitTotal",unitsInput-united);
                startActivity(goMonth);

            }
        });

        btngoMonthed.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent goMonthed = new Intent(MainmenuActivity.this,MonthedActivity.class);
                startActivity(goMonthed);

            }
        });
        btnshow1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectViewDialog();
            }
        });
        btnSimmulator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                simulatorUnitDialog();
            }
        });
        btnGoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectViewDialog2();
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        mRefClass1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int c1 = dataSnapshot.getValue(Integer.class);
                //Toast.makeText(MainmenuActivity.this, ""+c1, Toast.LENGTH_SHORT).show();

                float c2 = c1/100;
                PieView pieView = (PieView) findViewById(R.id.pieView1);
                pieView.setPercentage(c2);
                pieView.setPercentageBackgroundColor(Color.rgb(214, 92, 42));
                PieView animatedPie = (PieView) findViewById(R.id.pieView1);
                pieView.setInnerText("1: "+c1+" W");
                pieView.setTextColor(Color.rgb(214, 92, 42));
                pieView.setPercentageTextSize(24f);
                PieAngleAnimation animation = new PieAngleAnimation(animatedPie);
                animation.setDuration(300); //This is the duration of the animation in millis
                animatedPie.startAnimation(animation);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Error",""+databaseError);
                Toast.makeText(MainmenuActivity.this, "Device OFF", Toast.LENGTH_SHORT).show();
            }
        });
        mRefClass2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int c2 = dataSnapshot.getValue(Integer.class);

                float c22 = c2/100;
                PieView pieView2 = (PieView) findViewById(R.id.pieView2);
                pieView2.setPercentage(c22);
                pieView2.setPercentageBackgroundColor(Color.rgb(0, 153, 0));
                PieView animatedPie2 = (PieView) findViewById(R.id.pieView2);
                pieView2.setInnerText("2: "+c2+" W");
                pieView2.setTextColor(Color.rgb(0, 153, 0));
                pieView2.setPercentageTextSize(24f);
                PieAngleAnimation animation2 = new PieAngleAnimation(animatedPie2);
                animation2.setDuration(100); //This is the duration of the animation in millis
                animatedPie2.startAnimation(animation2);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Error",""+databaseError);
                Toast.makeText(MainmenuActivity.this, "Device Disconnected.", Toast.LENGTH_SHORT).show();
            }
        });
        mRefClass3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int c3 = dataSnapshot.getValue(Integer.class);

                float c33 = c3/100;
                PieView pieView3 = (PieView) findViewById(R.id.pieView3);
                pieView3.setPercentage(c33);
                if(c3>100){

                    pieView3.setTextColor(Color.rgb(255,255,255));
                }else{
                    pieView3.setTextColor(Color.rgb(51,0,0));
                }
//                pieView3.setTextColor(Color.rgb(51,0,0));
                //pieView3.setPercentageBackgroundColor(Color.rgb());
                PieView animatedPie3 = (PieView) findViewById(R.id.pieView3);
                pieView3.setInnerText("3: "+c3+" W");
                pieView3.setPercentageTextSize(24f);
                PieAngleAnimation animation3 = new PieAngleAnimation(animatedPie3);
                animation3.setDuration(100); //This is the duration of the animation in millis
                animatedPie3.startAnimation(animation3);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("Error",""+databaseError);
                Toast.makeText(MainmenuActivity.this, "Device OFF", Toast.LENGTH_SHORT).show();
            }
        });

        mRefall.addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                int all= dataSnapshot.getValue(Integer.class);

                if(all>3000){
                    setNotifications(notification1);
                    notification1++;
                }else {
                    notification1=1;
                }
                float call= all/100;
                PieView pieView3 = (PieView) findViewById(R.id.pieViewall);
                pieView3.setPercentage(call);
                //pieView3.setPercentageBackgroundColor(Color.rgb());
                PieView animatedPie3 = (PieView) findViewById(R.id.pieViewall);
                pieView3.setInnerText("All: "+all+" W");
                pieView3.setPercentageTextSize(25f);

                PieAngleAnimation animation3 = new PieAngleAnimation(animatedPie3);
                animation3.setDuration(1); //This is the duration of the animation in millis
                animatedPie3.startAnimation(animation3);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        unitRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                float unit = dataSnapshot.getValue(Float.class);
                txall.setText("ใช้ไฟฟ้า "+unit+" ยูนิต");
                unitsInput = unit;
                openDialog(n+=1);
                //                Toast.makeText(MainmenuActivity.this, "Connected.", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        Query query = select2.limitToLast(1);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String,Object> getData = (Map<String,Object>) dataSnapshot.getValue();
                united = Float.parseFloat(getData.get("unit").toString());

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
    public void openDialog(int status){
        if(status==0){
            Log.i("Connect","Connected");
            con.setIcon(R.drawable.iconfirebase);
            con.setTitle("โปรดรอ...");
            con.setMessage("กำลังเชื่อมต่อ firebase......");
            con.show();

        }else if (status==1){
            Log.i("Connect","Connected.");
            con.hide();
        }else {
            n = 3;
        }
    }

    private void selectViewDialog(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("หมายเหตุ");
        dialog.setCancelable(true);
        dialog.setMessage("กรุณาเลือกข้อมูลตามความต้องการ");
        dialog.setPositiveButton("ดูข้อมูลเป็นวัน", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent go = new Intent(MainmenuActivity.this,Class1activity.class);
                go.putExtra("findData","NO");
                go.putExtra("Mode","Day");
                startActivity(go);
            }
        });
        dialog.setNegativeButton("ดูข้อมูลเป็นเดือน", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent go = new Intent(MainmenuActivity.this,Viewgraph2.class);
                go.putExtra("Mode","Month");
                go.putExtra("findData","NO");
                startActivity(go);

            }
        });
        dialog.show();

    }
    private void selectViewDialog2(){
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("หมายเหตุ");
        dialog.setCancelable(true);
        dialog.setMessage("กรุณาเลือกข้อมูลตามความต้องการ");
        dialog.setPositiveButton("ดูข้อมูลเป็นวัน", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                Intent go = new Intent(MainmenuActivity.this,ListDayActivity.class);
                startActivity(go);
            }
        });
        dialog.setNegativeButton("ดูข้อมูลเป็นเดือน", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent go = new Intent(MainmenuActivity.this,ListMonthActivity.class);
                startActivity(go);
            }
        });
        dialog.show();

    }
    private void simulatorUnitDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(MainmenuActivity.this);
        builder.setTitle("กรุณาป้อนหน่วยไฟฟ้า");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_NUMBER);
        builder.setView(input);
        builder.setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent go = new Intent(MainmenuActivity.this,MonthActivity.class);
                simulatorUnit = input.getText().toString();
                float conversFloat = Float.parseFloat(simulatorUnit);
                go.putExtra("unitTotal", conversFloat);
                startActivity(go);
            }
        });
        builder.setNegativeButton("รีเซ็ตค่า", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                input.setText("");
            }
        });
        builder.create();
        builder.show();

    }
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void setNotifications(int chk){
        if(chk<2) {
            notification = new Notification.Builder(MainmenuActivity.this);
            notification.setSmallIcon(R.drawable.iconapp1);
            notification.setWhen(SystemClock.currentThreadTimeMillis());
            notification.setContentTitle("คำเตือน");
            notification.setTicker("มีข้อความเข้ามาใหม่");
            notification.setContentText("ใช้ไฟฟ้าในปริมาณมาก");
            Intent intent = new Intent(this, MainmenuActivity.class);
            PendingIntent pending = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pending);
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            notification.setSound(soundUri);
            //ใช้ในการจัดการการแจ้งเตือน
            NotificationManager nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

            nm.notify(id, notification.build());
        }
    }
    @Override
    public void onBackPressed() {
//        super.onBackPressed();

        AlertDialog.Builder dialog2 = new AlertDialog.Builder(MainmenuActivity.this);
        dialog2.setTitle("Exit");
        dialog2.setIcon(R.drawable.iconapp1);
        dialog2.setCancelable(true);
        dialog2.setMessage("Do you want to exit?");
        dialog2.setPositiveButton("Yes", new OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                finish();
                dialog.dismiss();
            }
        });
        dialog2.setNegativeButton("No", new OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog2.show();
    }

}