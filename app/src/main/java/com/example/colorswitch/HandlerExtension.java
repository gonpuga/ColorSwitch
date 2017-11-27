package com.example.colorswitch;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by gonpuga on 27/11/2017.
 */

public class HandlerExtension extends Handler {
    private final WeakReference<MainActivity> actividad;

    public HandlerExtension(MainActivity activity)
    {
        actividad=new WeakReference<MainActivity>(activity);
    }

    @Override
    public void handleMessage(Message mensaje)
    {
        MainActivity activity=actividad.get();
        if(activity!=null)
        {
            activity.resultField.setText(activity.resultField.getText() +
                    mensaje.getData().getString("result"));
        }
    }
}

