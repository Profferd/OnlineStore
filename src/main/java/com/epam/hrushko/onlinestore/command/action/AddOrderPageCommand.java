package com.epam.hrushko.onlinestore.command.action;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.OrderService;
import com.epam.hrushko.onlinestore.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class AddOrderPageCommand implements Command {
    private static final String PAGE = "WEB-INF/view/addOrder.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String USER = "user";
    private static final String TOTAL_COST = "totalCost";

    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        int userId = user.getId();

        try {
            OrderService orderService = new OrderServiceImpl();
            List<Order> orders = orderService.readByUser(userId);

            double totalPrice = orderService.calculateTotalCost(orders);
            requestContext.addRequestAttribute(TOTAL_COST, totalPrice);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }
        manager.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandType.FORWARD);
    }
}
