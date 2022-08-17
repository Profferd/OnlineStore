package com.epam.hrushko.onlinestore.command.action.auto;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import javax.servlet.http.HttpServletResponse;

/**
 * Class that show log in page
 */
public class LoginPageCommand implements Command {
    private static final String PAGE = "WEB-INF/view/login.jsp";
    private static final Logger LOGGER = Logger.getLogger(LoginPageCommand.class);
    /**
     * executing and show log in page
     * @param manager
     * @param response
     * @return command result
     */
    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();
        LOGGER.log(Level.INFO, "OK");
        LOGGER.info("OK");
        manager.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandType.FORWARD);
    }
}
