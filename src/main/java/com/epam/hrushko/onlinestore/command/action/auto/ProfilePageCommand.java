package com.epam.hrushko.onlinestore.command.action.auto;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.entity.UserInfo;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.CategoryService;
import com.epam.hrushko.onlinestore.service.UserInfoService;
import com.epam.hrushko.onlinestore.service.impl.CategoryServiceImpl;
import com.epam.hrushko.onlinestore.service.impl.UserInfoServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Class that show Profile page
 */
public class ProfilePageCommand implements Command {
    private static final String PAGE = "WEB-INF/view/profile.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String USER = "user";
    private static final String USER_INFORMATION = "userInfo";
    private static final String CATEGORIES = "category";

    /**
     * Executing and showing profile page
     * @param manager
     * @param response
     * @return Command Result
     */
    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        if (user == null) {
            manager.updateRequest(requestContext);
            return new CommandResult(PAGE, CommandType.FORWARD);
        }

        try {
            CategoryService categoryService = new CategoryServiceImpl();
            List<Category> categories = categoryService.readCategories();
            requestContext.addRequestAttribute(CATEGORIES, categories);

            int userInformationId = user.getUserInfoId();
            UserInfoService userInformationService = new UserInfoServiceImpl();
            Optional<UserInfo> userInformation = userInformationService.readById(userInformationId);
            userInformation.ifPresent(information -> requestContext.addRequestAttribute(USER_INFORMATION, information));
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }

        manager.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandType.FORWARD);
    }
}
