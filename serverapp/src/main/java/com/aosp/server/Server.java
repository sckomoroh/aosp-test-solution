package com.aosp.server;

import android.util.Log;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class Server extends Thread implements AutoCloseable {
    private static final String LOG_TAG = Server.class.getName();
    private final int _port;
    private final IClientAcceptHandler _clientAcceptHandler;
    private ServerSocket _serverSocket = null;
    private SocketAddress _endpoint;
    private boolean _isRunning;

    public Server(int port, IClientAcceptHandler clientAcceptHandler) {
        _port = port;
        _clientAcceptHandler = clientAcceptHandler;
        _endpoint = new InetSocketAddress(port);
        _isRunning = false;
    }

    public void initialize() throws IOException {
        Log.d(LOG_TAG, String.format("Initialize server. LOCALHOST. Port = %d\n", _port));

        _serverSocket = new ServerSocket();

        Log.d(LOG_TAG, "Bind server to address");
        _serverSocket.bind(_endpoint);
    }

    @Override
    public void run() {
        Log.d(LOG_TAG, "Start server");

        _isRunning = true;

        while (_isRunning) {
            try {
                Log.d(LOG_TAG, "Try to accept client");
                Socket socket = _serverSocket.accept();

                Log.d(LOG_TAG, "Client accepted");
                Client client = new Client(socket);
                client.initialize();

                Log.d(LOG_TAG, "Notify about new client");
                _clientAcceptHandler.onClientAccepted(client);
            } catch (IOException e) {
                e.printStackTrace();
                _isRunning = false;
            }
        }

        Log.d(LOG_TAG, "Server stopped");
    }

    @Override
    public void close() throws Exception {
        if (_serverSocket != null && !_serverSocket.isClosed()) {
            _serverSocket.close();
        }
    }
}
