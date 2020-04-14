package com.temp.objects;

import java.util.List;

public class CommandCall {
    public final String Name;
    public final List<String> Args;

    public CommandCall(String name, List<String> args) {
        Name = name;
        Args = args;
    }
}
