package com.example.colorswitch;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private static final String TAG=MainActivity.class.getName();
    private LinearLayout screenLayout = null;
    public EditText resultField;
    private HandlerExtension resultHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        screenLayout = (LinearLayout) findViewById(R.id.layoutMain);
        resultField = (EditText) findViewById(R.id.resultField);
        resultHandler=new HandlerExtension(this);
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

    public void longCalculation(View v) {
        new Thread(new Runnable() {
            public void run() {
                Log.v(TAG, "En longCalculation():" +
                        Thread.currentThread().getId());
                try {
                    Thread.currentThread().sleep(4000);
                } catch (InterruptedException e) { }

                Bundle msgBundle=new Bundle();
                msgBundle.putString("result", " Resultado");
                Message msg=new Message();
                msg.setData(msgBundle);
                resultHandler.sendMessage(msg);
            }
        }).start();
    }

}

