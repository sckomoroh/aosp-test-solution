package com.aosp.libcommands;

public abstract class CommandBase {
    public String getName() {
        String name = null;
        CommandDescription description = this.getClass().getAnnotation(CommandDescription.class);
        if (description != null) {
            name = description.name();
        }

        return name;
    }

    public String getDescription() {
        String desc = null;
        CommandDescription description = this.getClass().getAnnotation(CommandDescription.class);
        if (description != null) {
            desc = description.description();
        }

        return desc;
    }

    public abstract void execute();
}
