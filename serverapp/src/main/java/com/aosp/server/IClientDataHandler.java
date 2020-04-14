package com.aosp.server;

import java.io.IOException;

public interface IClientDataHandler {
    void onClientDataReceived(IClient client, String data) throws IOException;
}
