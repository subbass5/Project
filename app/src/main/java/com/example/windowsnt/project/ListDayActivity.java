package com.example.windowsnt.project;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by kritsana on 4/27/17.
 */

public class ListDayActivity extends AppCompatActivity {
    private List<String> mData = new ArrayList<String>();
    private ListView dListView;
    private ArrayAdapter<String> adapter;
    private DatabaseReference myRef;
    private Button btnGomain2;
    int numberData = 24;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listday);
        setTitle("ข้อมูลรายชั่วโมง");
        dListView = (ListView) findViewById(R.id.listViewDay);
        getData();
        btnGomain2 = (Button) findViewById(R.id.btnGoMainLD);
        btnGomain2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void getData(){
        myRef= FirebaseDatabase.getInstance().getReference();
        myRef = myRef.child("logHr");
        Query query = myRef.limitToLast(numberData);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String,Object> data = (Map<String,Object>) dataSnapshot.getValue();
                String time = data.get("time").toString();
                String wattAll =data.get("All").toString();
                mData.add(0,"Time Save : "+time+" \n Watt Use: "+wattAll);

                if(adapter==null){
                    adapter = new ArrayAdapter<String>(getApplicationContext(),
                            android.R.layout.simple_expandable_list_item_1,mData);
                    dListView.setAdapter(adapter);
                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
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
}
