package com.epam.hrushko.onlinestore.command;

public class CommandResult {
    private final String page;
    private final CommandType type;

    public CommandResult(String page, CommandType resultType) {
        this.page = page;
        type = resultType;
    }

    public boolean isRedirect() {
        return type == CommandType.REDIRECT;
    }

    public boolean isForward() {
        return type == CommandType.FORWARD;
    }

    public String getPage() {
        return page;
    }
}
