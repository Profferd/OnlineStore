package com.epam.hrushko.onlinestore.command.action;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.OrderService;
import com.epam.hrushko.onlinestore.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletResponse;

/**
 * Class that allow to delete order from basket
 */
public class DeleteOrderCommand implements Command {
    private static final String PAGE = "command=basket";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String ORDER_ID = "orderId";

    /**
     * Executes and delete order from basket by user
     * @param manager
     * @param response
     * @return Command Result
     */
    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        try {
            int orderId = Integer.parseInt(requestContext.getRequestParameter(ORDER_ID));
            OrderService orderService = new OrderServiceImpl();
            orderService.delete(orderId);
        } catch (ServiceException | NumberFormatException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }

        manager.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandType.REDIRECT);
    }
}
