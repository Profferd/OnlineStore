package com.epam.hrushko.onlinestore.command.action;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.OrderService;
import com.epam.hrushko.onlinestore.service.ProductService;
import com.epam.hrushko.onlinestore.service.impl.OrderServiceImpl;
import com.epam.hrushko.onlinestore.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

public class AddToBucketCommand implements Command {
    private static final String PAGE = "command=catalog";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATEGORY_ID_PARAMETER = "&categoryId=";
    private static final String MESSAGE_PARAMETER = "&message=";
    private static final String USER = "user";
    private static final String PRODUCT_ID = "productId";
    private static final String QUANTITY = "quantity";
    private static final String OK = "ok";
    private static final String ERROR = "error";

    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        User user = (User) requestContext.getSessionAttribute(USER);
        int userId = user.getId();
        try {
            int productId = Integer.parseInt(requestContext.getRequestParameter(PRODUCT_ID));
            int quantity = Integer.parseInt(requestContext.getRequestParameter(QUANTITY));
            OrderService orderService = new OrderServiceImpl();
            String message = OK;
            boolean result = orderService.create(userId, productId, quantity);
            if (!result) message = ERROR;
            ProductService productService = new ProductServiceImpl();
            Optional<Product> product = productService.readById(productId);
            int categoryId = 0;
            if (product.isPresent()) {
                categoryId = product.get().getCategoryId();
            }

            manager.updateRequest(requestContext);
            return new CommandResult(PAGE + CATEGORY_ID_PARAMETER + categoryId
                    + MESSAGE_PARAMETER + message, CommandType.REDIRECT);
        } catch (ServiceException | NumberFormatException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }
    }
}
