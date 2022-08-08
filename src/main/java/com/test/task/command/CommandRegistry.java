package com.test.task.command;

import com.test.task.presentation.ShowMainPageCommand;

import java.util.Arrays;
import java.util.List;

public enum CommandRegistry {
    DEFAULT(ShowMainPageCommand.INSTANCE, "");

    private final Command command;
    private final String path;





    CommandRegistry(Command command, String path) {
        this.command = command;
        this.path = path;

    }

    public Command getCommand() {
        return command;
    }



    static Command of(String name) {
        for (CommandRegistry constant : values()) {
            if (constant.path.equalsIgnoreCase(name)) {
                return constant.command;
            }
        }
        return DEFAULT.command;
    }
}
