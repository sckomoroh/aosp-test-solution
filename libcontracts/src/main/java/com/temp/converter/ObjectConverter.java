package com.temp.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.temp.contracts.Command;
import com.temp.contracts.CommandActionRequest;
import com.temp.contracts.CommandActionResponse;
import com.temp.contracts.CommandBase;
import com.temp.contracts.ECommandType;
import com.temp.contracts.EStatus;
import com.temp.objects.CommandCall;
import com.temp.objects.CommandResponse;

import java.io.IOException;
import java.io.StringWriter;

public class ObjectConverter {
    private ObjectMapper _objectMapper = new ObjectMapper();

    public CommandBase getCommand(String jsonCommand) throws JsonProcessingException {
        return _objectMapper.readValue(jsonCommand, CommandBase.class);
    }

    public String getErrorResponse(String errMsg) throws IOException {
        CommandBase cmd = new CommandBase();
        cmd.setStatus(EStatus.ERROR);
        cmd.setErrorMessage(errMsg);
        cmd.setCommandType(ECommandType.GeneralResponse);

        StringWriter writer = new StringWriter();
        _objectMapper.writeValue(writer, CommandBase.class);

        return writer.toString();
    }

    public CommandCall getCommandCall(String jsonInput) throws JsonProcessingException {
        CommandActionRequest cmd = _objectMapper.readValue(jsonInput, CommandActionRequest.class);
        return new CommandCall(cmd.getCommandName(), cmd.getArguments());
    }

    public String getCommandResponse(CommandResponse response) throws IOException {
        StringWriter writer = new StringWriter();
        CommandActionResponse jsonResponse = new CommandActionResponse();
        jsonResponse.setResponse(response.Response);
        jsonResponse.setSuccess(response.IsSuccess);
        _objectMapper.writeValue(writer, CommandActionResponse.class);

        return writer.toString();
    }
}
