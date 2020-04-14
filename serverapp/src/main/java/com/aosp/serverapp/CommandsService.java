package com.aosp.serverapp;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class CommandsService extends Service {
    private final ICommandServiceImpl _binder = new ICommandServiceImpl();

    public CommandsService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return _binder.asBinder();
    }
}
