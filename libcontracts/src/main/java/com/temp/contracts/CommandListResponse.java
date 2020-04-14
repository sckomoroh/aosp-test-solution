package com.temp.contracts;

import java.util.List;

public class CommandListResponse {
    private List<Command> _commandList;

    public List<Command> getCommands() {
        return _commandList;
    }

    public void setCommands(List<Command> cmdList) {
        _commandList = cmdList;
    }
}
