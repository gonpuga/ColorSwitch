package com.example.colorswitch;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG=MainActivity.class.getName();
    private LinearLayout screenLayout = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenLayout = (LinearLayout) findViewById(R.id.layoutMain);
        Log.v(TAG, "En onCreate():" + Thread.currentThread().getId());
    }

    public void redOnClick(View v) {
        Log.v(TAG, "En redOnClick():"+Thread.currentThread().getId());
        screenLayout.setBackgroundColor(Color.RED);
    }
    public void greenOnClick(View v) {
        Log.v(TAG, "En greenOnClick():"+Thread.currentThread().getId());
        screenLayout.setBackgroundColor(Color.GREEN);
    }

    public void blueOnClick(View v) {
        Log.v(TAG, "En blueOnClick():"+Thread.currentThread().getId());
        screenLayout.setBackgroundColor(Color.BLUE);
    }
}

