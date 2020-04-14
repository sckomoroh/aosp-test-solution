package com.temp.contracts;

import java.util.List;

public class Command {
    private String _name;
    private List<Command> _commands;

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        _name = name;
    }

    public List<Command> getCommands() {
        return _commands;
    }

    public void setCommands(List<Command> commands) {
        _commands = commands;
    }
}
