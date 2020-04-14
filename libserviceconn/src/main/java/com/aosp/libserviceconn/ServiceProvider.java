package com.aosp.libserviceconn;

import android.os.IBinder;
import android.os.ServiceManager;

import com.mitsubishielectric.ahu.normalrvc.INormalRVCService;

public class ServiceProvider {
    public static final ServiceProvider Provider = new ServiceProvider();

    private INormalRVCService _service;

    public void bind() {
        IBinder binder = ServiceManager.getService("com.mitsubishielectric.ahu.normalrvc..NormalRVCService");
        _service = INormalRVCService.Stub.asInterface(binder);
    }

    public INormalRVCService getService() {
        return _service;
    }
}
