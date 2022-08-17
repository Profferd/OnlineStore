package com.epam.hrushko.onlinestore.command.action;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.entity.Promotion;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.CategoryService;
import com.epam.hrushko.onlinestore.service.PromotionService;
import com.epam.hrushko.onlinestore.service.impl.CategoryServiceImpl;
import com.epam.hrushko.onlinestore.service.impl.PromotionServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class PromotionPageCommand implements Command {
    private static final String PAGE = "WEB-INF/view/promotion.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String PROMOTIONS = "promotion";
    private static final String CATEGORIES = "category";
    public static final String NUMBER_OF_PAGES = "nuPages";
    public static final String CURRENT_PAGE = "page";
    public static final String GO_TO_PAGE = "goToPage";
    private static final int RECORDS_PER_PAGE = 2;

    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        try {
            int page = 1;
            if (requestContext.getRequestParameter(CURRENT_PAGE) != null) {
                page = Integer.parseInt(requestContext.getRequestParameter(CURRENT_PAGE));
            }
            PromotionService promotionService = new PromotionServiceImpl();
            List<Promotion> promotions = promotionService.read();
            List<Promotion> promotions1 = new ArrayList<>();
            for (int i = ((page - 1) * RECORDS_PER_PAGE); i < Math.min(page * RECORDS_PER_PAGE,promotions.size()); i++) {
                promotions1.add(promotions.get(i));
            }
            int numberOfPages = (promotions.size() + RECORDS_PER_PAGE -1) / RECORDS_PER_PAGE;
            requestContext.addRequestAttribute(PROMOTIONS, promotions1);
            requestContext.addRequestAttribute(NUMBER_OF_PAGES, numberOfPages);
            requestContext.addRequestAttribute(CURRENT_PAGE, page);

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
