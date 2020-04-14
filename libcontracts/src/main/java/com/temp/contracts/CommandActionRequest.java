package com.temp.contracts;

import java.util.List;

public class CommandActionRequest {
    private String _commandName;
    private List<String> _arguments;

    public String getCommandName() {
        return _commandName;
    }

    public void setCommandName(String value) {
        _commandName = value;
    }

    public List<String> getArguments() {
        return _arguments;
    }

    public void setArguments(List<String> value) {
        _arguments = value;
    }
}
