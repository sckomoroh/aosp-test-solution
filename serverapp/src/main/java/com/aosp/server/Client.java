package com.aosp.server;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client extends Thread implements IClient {
    private static final String LOG_TAG = Client.class.getName();

    private final Socket _socket;
    private IClientDataHandler _clientDataHandler;
    private BufferedReader _reader;
    private BufferedWriter _writer;
    private boolean _isRunning;

    public Client(Socket socket) {
        _socket = socket;
        _isRunning = false;
    }

    @Override
    public void initialize() throws IOException {
        _reader = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
        _writer = new BufferedWriter(new OutputStreamWriter(_socket.getOutputStream()));
    }

    @Override
    public void setDataHandler(IClientDataHandler clientDataHandler) {
        _clientDataHandler = clientDataHandler;
    }

    @Override
    public void writeData(String data) throws IOException {
        Log.d(LOG_TAG, String.format("Send response: len=%d data='%s'", data.length(), data));
        _writer.write(data.length());
        _writer.write(data);
        _writer.flush();
    }

    @Override
    public void run() {
        Log.d(LOG_TAG, "Start client");
        _isRunning = true;

        while (_isRunning) {
            try {
                int len = _reader.read();
                if (len > 0) {
                    System.out.printf("Data size: %d\n", len);
                    char[] buffer = new char[len];
                    _reader.read(buffer);
                    _clientDataHandler.onClientDataReceived(this, new String(buffer));
                } else if (len == -1) {
                    _isRunning = false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                _isRunning = false;
            }
        }

        Log.d(LOG_TAG, "Client work completed");
    }
}
