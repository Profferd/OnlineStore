package com.epam.hrushko.onlinestore.command.action;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.entity.*;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.*;
import com.epam.hrushko.onlinestore.service.impl.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Class that show all orders page
 */
public class ViewOrdersPageCommand implements Command {
    private static final String PAGE = "WEB-INF/view/viewOrder.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATEGORIES = "category";
    private static final String USER_ORDERS = "userOrder";
    private static final String USERS = "user";
    private static final String PRODUCTS = "product";
    private static final String ORDERS = "order";
    private static final String USER_INFORMATION = "userInfo";
    private static final String EXPECTED = "pending";

    /**
     * Executes and show all user orders on the page
     * @param manager
     * @param response
     * @return Command Result
     */
    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        try {
            CategoryService categoryService = new CategoryServiceImpl();
            List<Category> categories = categoryService.readCategories();
            requestContext.addRequestAttribute(CATEGORIES, categories);

            UserOrderService userOrderService = new UserOrderServiceImpl();
            List<UserOrder> userOrders = userOrderService.readByStatus(EXPECTED);
            requestContext.addRequestAttribute(USER_ORDERS, userOrders);

            OrderService orderService = new OrderServiceImpl();
            List<Order> orders = orderService.readOrders(userOrders);
            requestContext.addRequestAttribute(ORDERS, orders);

            ProductService productService = new ProductServiceImpl();
            List<Product> products = productService.readFromOrders(orders);
            requestContext.addRequestAttribute(PRODUCTS, products);

            UserService userService = new UserServiceImpl();
            List<User> users = userService.getUser(orders);
            requestContext.addRequestAttribute(USERS, users);

            UserInfoService userInformationService = new UserInfoServiceImpl();
            List<UserInfo> userInformation = userInformationService.readFromUsers(users);
            requestContext.addRequestAttribute(USER_INFORMATION, userInformation);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }

        manager.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandType.FORWARD);
    }
}
