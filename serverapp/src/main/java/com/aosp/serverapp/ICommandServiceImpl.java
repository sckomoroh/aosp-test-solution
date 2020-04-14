package com.aosp.serverapp;

import android.os.RemoteException;

import com.aosp.server.ClientsHolder;
import com.aosp.server.Server;

import java.util.List;

public class ICommandServiceImpl extends ICommandService.Stub {
    @Override
    public String executeCommand(String command, List<String> args) throws RemoteException {
        return null;
    }

    @Override
    public List<String> queryCommands() throws RemoteException {
        return null;
    }

    @Override
    public void start() throws RemoteException {
        try {
            ClientsHolder clientsHolder = new ClientsHolder(this);
            try (Server server = new Server(6009, clientsHolder)) {

                server.initialize();

                server.start();

                server.join();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
