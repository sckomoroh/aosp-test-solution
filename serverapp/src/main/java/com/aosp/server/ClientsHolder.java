package com.aosp.server;

import android.util.Log;

import com.aosp.serverapp.ICommandService;

import java.util.ArrayList;
import java.util.List;

public class ClientsHolder implements IClientAcceptHandler {
    private static final String LOG_TAG = ClientsHolder.class.getName();

    private final List<DataProcessor> _dataProcessors = new ArrayList<>();
    private final ICommandService _commandService;

    public ClientsHolder(ICommandService commandService) {
        _commandService = commandService;
    }

    @Override
    public void onClientAccepted(IClient client) {
        Log.d(LOG_TAG, "New client accepted");
        DataProcessor dataProcessor = new DataProcessor(_commandService);
        client.setDataHandler(dataProcessor);
        client.run();
        _dataProcessors.add(dataProcessor);
    }
}
