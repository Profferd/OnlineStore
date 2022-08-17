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
import com.epam.hrushko.onlinestore.service.UserOrderService;
import com.epam.hrushko.onlinestore.service.impl.OrderServiceImpl;
import com.epam.hrushko.onlinestore.service.impl.UserOrderServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

/**
 * Class that allow to arrange order
 */
public class ConfirmOrderCommand implements Command {
    private static final String ADD_ORDER_PAGE = "WEB-INF/view/addOrder.jsp";
    private static final String ORDERS_PAGE = "command=myOrders";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String ERROR_MESSAGE = "errorMessage";
    private static final String ADDRESS = "address";
    private static final String DELIVERY_DATE = "delivery-date";
    private static final String USER = "user";
    private static final String TOTAL_COST = "totalCost";

    /**
     * Executes and take important information from site and send request to administrator to approve arrange
     * @param manager
     * @param response
     * @return
     */
    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        Optional<String> address = Optional.ofNullable(requestContext.getRequestParameter(ADDRESS));
        Optional<String> deliveryDate = Optional.ofNullable(requestContext.getRequestParameter(DELIVERY_DATE));


        try {
            User user = (User) requestContext.getSessionAttribute(USER);
            int userId = user.getId();
            OrderService orderService = new OrderServiceImpl();
            List<Order> orders = orderService.readByUser(userId);
            double totalPrice = orderService.calculateTotalCost(orders);
            if (address.isPresent() && deliveryDate.isPresent()) {
                UserOrderService userOrderService = new UserOrderServiceImpl();
                boolean result = userOrderService.create(orders, address.get(), deliveryDate.get(), totalPrice);
                if (result) {
                    manager.updateRequest(requestContext);
                    return new CommandResult(ORDERS_PAGE, CommandType.REDIRECT);
                }
            }
            requestContext.addRequestAttribute(TOTAL_COST, totalPrice);
            requestContext.addRequestAttribute(ERROR_MESSAGE, true);
            manager.updateRequest(requestContext);
            return new CommandResult(ADD_ORDER_PAGE, CommandType.FORWARD);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }
    }
}
