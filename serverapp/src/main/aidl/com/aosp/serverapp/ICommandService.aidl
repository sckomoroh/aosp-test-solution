// ICommandService.aidl
package com.aosp.serverapp;

interface ICommandService {
    String executeCommand(String command, in List<String> args);

    List<String> queryCommands();

    void start();
}
