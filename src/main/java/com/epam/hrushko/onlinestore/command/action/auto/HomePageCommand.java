package com.epam.hrushko.onlinestore.command.action.auto;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.CategoryService;
import com.epam.hrushko.onlinestore.service.impl.CategoryServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class that show home page command
 */
public class HomePageCommand implements Command {
    private static final String PAGE = "WEB-INF/view/home.jsp";
    private static final String ERROR = "WEB-INF/view/error.jsp";
    private static final String CATEGORIES = "category";
    private static final String PAGES = "pages";

    /**
     * executing and show home page
     * @param manager
     * @param response
     * @return
     */
    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        try {
            String pages = "4";
            CategoryService categoryService = new CategoryServiceImpl();
            List<Category> categories = categoryService.readCategories();
            requestContext.addRequestAttribute(CATEGORIES, categories);
            requestContext.addRequestAttribute(PAGES, pages);
        } catch (ServiceException e) {
            return new CommandResult(ERROR, CommandType.FORWARD);
        }

        manager.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandType.FORWARD);
    }
}
