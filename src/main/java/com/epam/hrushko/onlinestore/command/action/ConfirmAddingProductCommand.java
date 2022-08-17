package com.epam.hrushko.onlinestore.command.action;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.ProductService;
import com.epam.hrushko.onlinestore.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Class that complete adding of product by administrator
 */
public class ConfirmAddingProductCommand implements Command {
    private static final String PAGE = "command=addProduct";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String PRODUCT_NAME = "product-name";
    private static final String PHOTO = "photo";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private static final String DESCRIPTION = "description";
    private static final String AVAILABILITY = "availability";
    private static final String MESSAGE_PARAMETER = "&message=";
    private static final String ERROR = "error";
    private static final String OK = "ok";

    /**
     * Executes and take all information from site and place it to database
     * administrator only
     * @param manager
     * @param response
     * @return Command Result
     */
    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();
        String message = ERROR;

        Optional<String> productName = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_NAME));
        Optional<String> photo = Optional.ofNullable(requestContext.getRequestParameter(PHOTO));
        Optional<String> price = Optional.ofNullable(requestContext.getRequestParameter(PRICE));
        Optional<String> category = Optional.ofNullable(requestContext.getRequestParameter(CATEGORY));
        Optional<String> description = Optional.ofNullable(requestContext.getRequestParameter(DESCRIPTION));
        Optional<String> availability = Optional.ofNullable(requestContext.getRequestParameter(AVAILABILITY));

        try {
            if (productName.isPresent() && photo.isPresent() && price.isPresent() && category.isPresent() &&
                    description.isPresent()) {
                boolean status = false;
                if (availability.isPresent()) {
                    status = true;
                }

                ProductService productService = new ProductServiceImpl();
                boolean result = productService.create(productName.get(),
                        photo.get(), price.get(), category.get(), status, description.get());
                if (result) {
                    message = OK;
                }
            }
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }

        manager.updateRequest(requestContext);
        return new CommandResult(PAGE + MESSAGE_PARAMETER + message, CommandType.REDIRECT);
    }
}
