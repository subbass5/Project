package com.example.windowsnt.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class MonthedActivity extends Activity {
    private Button btngomain;
    Bundle bundle;
    TextView txtHader;
    String year,month,date,unit,price;
    int countMonth = 0;

    private List<String> mChats = new ArrayList<String>();
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;

    DatabaseReference mDbRef;
    DatabaseReference select;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthed);
        mDbRef = FirebaseDatabase.getInstance().getReference();
        select = mDbRef.child("temp");
         getData();
        bundle = getIntent().getExtras();
        txtHader = (TextView) findViewById(R.id.txtHaed2);
        mListView = (ListView) findViewById(R.id.listview1);
        btngomain = (Button) findViewById(R.id.btnbacktomain2);
        btngomain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });
        if(countMonth>3){
            select.removeValue();
        }

    }
    private void getData(){

        mDbRef = FirebaseDatabase.getInstance().getReference();
        select = mDbRef.child("Electricity back");
        Query query = select.limitToLast(3);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String,Object>  data = (Map<String,Object>) dataSnapshot.getValue();
                 date = data.get("date").toString();
                 unit = data.get("unit").toString();
                 price = data.get("price").toString();

                mChats.add("    "+date+"                   "+unit+"                "+price);

                    mAdapter = new ArrayAdapter<String>(getApplicationContext()
                            ,android.R.layout.simple_expandable_list_item_1,mChats);
                    mListView.setAdapter(mAdapter);
               System.out.println(mListView.toString());
                mAdapter.notifyDataSetChanged();
                countMonth++;



            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    onRestart();
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
