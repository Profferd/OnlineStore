package com.epam.hrushko.onlinestore.command.action.auto;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.UserService;
import com.epam.hrushko.onlinestore.service.impl.UserServiceImpl;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class SignUpCommand implements Command {
    private static final String LOG_UP_PAGE = "WEB-INF/view/signup.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String EMAIL = "email";
    private static final String PASSWORD_FIRST = "password-first";
    private static final String PASSWORD_SECOND = "password-second";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String PHONE = "phone";
    private static final String MESSAGE = "message";
    private static final String ERROR = "error";
    private static final String OK = "ok";

    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();
        String message = ERROR;

        Optional<String> email = Optional.ofNullable(requestContext.getRequestParameter(EMAIL));
        Optional<String> passwordFirst = Optional.ofNullable(requestContext.getRequestParameter(PASSWORD_FIRST));
        Optional<String> passwordSecond = Optional.ofNullable(requestContext.getRequestParameter(PASSWORD_SECOND));
        Optional<String> name = Optional.ofNullable(requestContext.getRequestParameter(NAME));
        Optional<String> surname = Optional.ofNullable(requestContext.getRequestParameter(SURNAME));
        Optional<String> phone = Optional.ofNullable(requestContext.getRequestParameter(PHONE));

        try {
            if (email.isPresent() && passwordFirst.isPresent() && passwordSecond.isPresent() &&
                    name.isPresent() && surname.isPresent() && phone.isPresent()) {
                if (passwordFirst.get().equals(passwordSecond.get())) {
                    UserService userService = new UserServiceImpl();
                    boolean result = userService.register(name.get(), surname.get(), email.get(), phone.get(), DigestUtils.sha1Hex(passwordFirst.get()));
                    if (result) message = OK;
                }
            }
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }

        requestContext.addRequestAttribute(MESSAGE, message);
        manager.updateRequest(requestContext);
        return new CommandResult(LOG_UP_PAGE, CommandType.FORWARD);
    }
}
