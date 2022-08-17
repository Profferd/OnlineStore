package com.epam.hrushko.onlinestore.command.action;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.UserOrderService;
import com.epam.hrushko.onlinestore.service.impl.UserOrderServiceImpl;

import javax.servlet.http.HttpServletResponse;

/**
 * Class that allow administrators to cancel arrange from user
 */
public class DeleteUserOrderCommand implements Command {
    private static final String PAGE = "command=viewOrders";
    private static final String USER_ORDER_ID = "orderId";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CANCELED = "canceled";

    /**
     * Executes and canceled user order
     * @param helper
     * @param response
     * @return Command Result
     */
    @Override
    public CommandResult execute(RequestManager helper, HttpServletResponse response) {
        Requests requestContext = helper.createContext();

        try {
            int userOrderId = Integer.parseInt(requestContext.getRequestParameter(USER_ORDER_ID));
            UserOrderService userOrderService = new UserOrderServiceImpl();
            userOrderService.updateStatusById(userOrderId, CANCELED);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }

        helper.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandType.REDIRECT);
    }
}
