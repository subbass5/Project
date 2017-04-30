package com.example.windowsnt.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;


public class MainActivity extends Activity {
    private  Handler hd;
    private Runnable rn;
      long Time = 3000L;
      long delay_time;
            ImageView im1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActionBar().hide();
        setContentView(R.layout.activity_main);

        im1 = (ImageView) findViewById(R.id.imageView2);
        im1.setImageResource(R.drawable.a);
        hd = new Handler();
        rn = new Runnable() {
            @Override
            public void run() {
                Intent goMain = new Intent(MainActivity.this,MainmenuActivity.class);
                startActivity(goMain);
                finish();

            }
        };

    }

    @Override
    protected void onResume() {
        super.onResume();
        delay_time = Time;
        hd.postDelayed(rn,delay_time);
        Time = System.currentTimeMillis();
    }

    @Override
    protected void onPause() {
        super.onPause();
        hd.removeCallbacks(rn);
        Time = delay_time - (System.currentTimeMillis() - Time);
    }
}
