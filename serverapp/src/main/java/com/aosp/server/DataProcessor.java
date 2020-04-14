package com.aosp.server;

import com.aosp.serverapp.ICommandService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.temp.contracts.Command;
import com.temp.contracts.CommandBase;
import com.temp.contracts.ECommandType;
import com.temp.converter.ObjectConverter;
import com.temp.objects.CommandCall;

import java.io.IOException;

public class DataProcessor implements IClientDataHandler {
    private final ObjectConverter _converter;
    private final ICommandService _commandService;

    public DataProcessor(ICommandService commandService) {
        _converter = new ObjectConverter();
        _commandService = commandService;
    }

    @Override
    public void onClientDataReceived(IClient client, String data) throws IOException {
        // TODO: Process data
        System.out.printf("Process data: %s\n", data);

        String response = "";
        CommandBase command = _converter.getCommand(data);
        ECommandType cmdType = command.getCommandType();
        switch (cmdType) {
            case ActionRequest:
                response = processAction(command.getContent());
                break;
            case CommandsRequest:
                response = processCommands();
                break;
            default:
                response = processUnknown(cmdType);
        }

        client.writeData(response);
    }

    private String processAction(String cmdContent) throws JsonProcessingException {
        CommandCall cmdCall = _converter.getCommandCall(cmdContent);
        // TODO: Process command call

        return "";
    }

    private String processCommands() {
        // TODO: Process commands list
        return "";
    }

    private String processUnknown(ECommandType cmdType) throws IOException {
        return _converter.getErrorResponse("Invalid request type: " + cmdType);
    }
}
