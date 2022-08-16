package com.epam.hrushko.onlinestore.command.action.auto;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;

import javax.servlet.http.HttpServletResponse;

/**
 * Class that show sign up page
 */
public class SignUpPageCommand implements Command {
    private static final String PAGE = "WEB-INF/view/signup.jsp";

    /**
     * Executing and show sign up page
     * @param manager
     * @param response
     * @return
     */
    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        manager.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandType.FORWARD);
    }
}
