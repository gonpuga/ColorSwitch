package com.example.colorswitch;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private LinearLayout screenLayout = null;
    private static final String
            LOCAL_INTENT="com.example.colorswitch.CUSTOM_ACTION";
    public EditText resultField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenLayout = findViewById(R.id.layoutMain);
        resultField = findViewById(R.id.resultField);
        Log.v(TAG, "En onCreate():" + Thread.currentThread().getId());
    }

    public void redOnClick(View v) {
        Log.v(TAG, "En redOnClick():" + Thread.currentThread().getId());
        screenLayout.setBackgroundColor(Color.RED);
    }

    public void greenOnClick(View v) {
        Log.v(TAG, "En greenOnClick():" + Thread.currentThread().getId());
        screenLayout.setBackgroundColor(Color.GREEN);
    }

    public void blueOnClick(View v) {
        Log.v(TAG, "En blueOnClick():" + Thread.currentThread().getId());
        screenLayout.setBackgroundColor(Color.BLUE);
    }

    public void longCalculation(View v) {
        new Thread(new Runnable() {
            public void run() {
                Log.v(TAG, "En longCalculation():" +
                        Thread.currentThread().getId());
                try {
                    Thread.currentThread().sleep(4000);
                } catch (InterruptedException e) { }

                Intent intent=new Intent(LOCAL_INTENT);
                intent.putExtra("result", "Resultado");
                LocalBroadcastManager.getInstance(MainActivity.this)
                        .sendBroadcast(intent);
            }
        }).start();
    }

    @Override protected void onPause() {
        super.onPause();
        // unregister local broadcast
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mReceiver);
    }

    @Override protected void onResume() {
        super.onResume();
        // register local broadcast
        IntentFilter filter = new IntentFilter(LOCAL_INTENT);
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(mReceiver, filter);
    }

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override public void onReceive(Context context, Intent intent) {
            String resultado = intent.getStringExtra("result");
            resultField.setText(resultField.getText() + " " + resultado);
        }
    };
}
