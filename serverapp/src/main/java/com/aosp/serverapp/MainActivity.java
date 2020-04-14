package com.aosp.serverapp;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

public class MainActivity extends Activity {
    private final static String LOG_TAG = MainActivity.class.getName();
    private ICommandService _serviceImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final ServiceConnection serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {
                Log.d(LOG_TAG, "Service connected");
                _serviceImpl = (ICommandService) service;
                try {
                    Log.d(LOG_TAG, "Start command server");
                    _serviceImpl.start();
                } catch (RemoteException e) {
                    Log.d(LOG_TAG, "Failed to start command server: ");
                    e.printStackTrace();
                }
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {
                Log.d(LOG_TAG, "Service disconnected");
            }
        };

        ComponentName componentName = new ComponentName("com.aosp.serverapp", "com.aosp.serverapp.CommandsService");
        Intent intent = new Intent();
        intent.setAction("LocalConnection");
        intent.setComponent(componentName);

        Log.d(LOG_TAG, "bind to service");
        boolean bindResult = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);

        Log.d(LOG_TAG, "Bind result: " + bindResult);
    }
}
