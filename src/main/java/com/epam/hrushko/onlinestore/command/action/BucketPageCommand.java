package com.epam.hrushko.onlinestore.command.action;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.CategoryService;
import com.epam.hrushko.onlinestore.service.OrderService;
import com.epam.hrushko.onlinestore.service.ProductService;
import com.epam.hrushko.onlinestore.service.PromotionService;
import com.epam.hrushko.onlinestore.service.impl.CategoryServiceImpl;
import com.epam.hrushko.onlinestore.service.impl.OrderServiceImpl;
import com.epam.hrushko.onlinestore.service.impl.ProductServiceImpl;
import com.epam.hrushko.onlinestore.service.impl.PromotionServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Class that show basket page
 */
public class BucketPageCommand implements Command {
    private static final String PAGE = "WEB-INF/view/bucket.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATEGORIES = "category";
    private static final String ORDERS = "order";
    private static final String USER = "user";
    private static final String PRODUCTS = "product";
    private static final String TOTAL_COST = "totalCost";
    private static final String NEW_PRICES = "newPrice";

    /**
     * Executes basket page and show it
     * Also show all product that was added there
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

            int userId = user.getId();
            OrderService orderService = new OrderServiceImpl();
            List<Order> orders = orderService.readByUser(userId);
            requestContext.addRequestAttribute(ORDERS, orders);

            double totalPrice = orderService.calculateTotalCost(orders);
            requestContext.addRequestAttribute(TOTAL_COST, totalPrice);

            ProductService productService = new ProductServiceImpl();
            List<Product> products = productService.readFromOrders(orders);
            requestContext.addRequestAttribute(PRODUCTS, products);

            PromotionService promotionService = new PromotionServiceImpl();
            Map<String, Double> newPrices = promotionService.getNewPrices(products);
            requestContext.addRequestAttribute(NEW_PRICES, newPrices);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }

        manager.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandType.FORWARD);
    }
}
