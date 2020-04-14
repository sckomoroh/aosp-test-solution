package com.aosp.server;

import java.io.IOException;

public interface IClient extends Runnable {
    void initialize() throws IOException;

    void writeData(String data) throws IOException;

    void setDataHandler(IClientDataHandler clientDataHandler);
}
