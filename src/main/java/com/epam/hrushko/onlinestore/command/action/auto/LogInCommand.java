package com.epam.hrushko.onlinestore.command.action.auto;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.entity.Role;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.RoleService;
import com.epam.hrushko.onlinestore.service.UserService;
import com.epam.hrushko.onlinestore.service.impl.RoleServiceImpl;
import com.epam.hrushko.onlinestore.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class LogInCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger();

    private static final String HOME = "command=home";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String LOGIN_PAGE = "WEB-INF/view/login.jsp";
    private static final String PASSWORD_PARAMETER = "password";
    private static final String EMAIL_PARAMETER = "email";
    private static final String USER = "user";
    private static final String ROLE = "role";
    private static final String ERROR_MESSAGE = "error";

    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        String password = requestContext.getRequestParameter(PASSWORD_PARAMETER);
        String login = requestContext.getRequestParameter(EMAIL_PARAMETER);

        try {
            UserService userService = new UserServiceImpl();
            //password = DigestUtils.sha1Hex(password);
            Optional<User> optionalResult = userService.login(login, password);
            if (optionalResult.isPresent()) {
                requestContext.addSessionAttribute(USER, optionalResult.get());
                RoleService roleService = new RoleServiceImpl();
                Optional<Role> role = roleService.findById(optionalResult.get().getRoleId());
                role.ifPresent(value -> requestContext.addSessionAttribute(ROLE, value));

                manager.updateRequest(requestContext);
                return new CommandResult(HOME, CommandType.REDIRECT);
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, "Cannot login");
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }

        requestContext.addRequestAttribute(ERROR_MESSAGE, true);
        manager.updateRequest(requestContext);
        return new CommandResult(LOGIN_PAGE, CommandType.FORWARD);
    }
}
