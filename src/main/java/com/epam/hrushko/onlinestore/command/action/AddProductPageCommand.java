package com.epam.hrushko.onlinestore.command.action;

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
 * Class that show product page adder to administrator
 */
public class AddProductPageCommand implements Command {
    private static final String PAGE = "WEB-INF/view/addProduct.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATEGORIES = "category";

    /**
     * Executes and show product adding page for administrator
     * @param manager
     * @param response
     * @return
     */
    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        try {
            CategoryService categoryService = new CategoryServiceImpl();
            List<Category> categories = categoryService.readCategories();
            requestContext.addRequestAttribute(CATEGORIES, categories);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }

        manager.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandType.FORWARD);
    }
}
