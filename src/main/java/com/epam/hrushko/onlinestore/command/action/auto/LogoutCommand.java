package com.epam.hrushko.onlinestore.command.action.auto;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;

import javax.servlet.http.HttpServletResponse;

/**
 * Class that executing log out command
 */
public class LogoutCommand implements Command {
    private static final String HOME = "command=home";
    private static final String USER = "user";
    private static final String ROLE = "role";

    /**
     * Executing and remove user from session
     * @param manager
     * @param response
     * @return command result
     */
    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requests = manager.createContext();
        requests.removeSessionAttribute(USER);
        requests.removeSessionAttribute(ROLE);
        manager.updateRequest(requests);
        return new CommandResult(HOME, CommandType.REDIRECT);
    }
}
