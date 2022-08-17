package com.epam.hrushko.onlinestore.command.action;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.ProductService;
import com.epam.hrushko.onlinestore.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * Class that allow to edit product
 */
public class ConfirmEditProductCommand implements Command {
    private static final String PAGE = "command=catalog";
    private static final String CATEGORY_PARAMETER = "&categoryId=";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String PRODUCT_NAME = "product-name";
    private static final String PHOTO = "photo";
    private static final String PRICE = "price";
    private static final String CATEGORY = "category";
    private static final String PROMOTION = "promotion";
    private static final String DESCRIPTION = "description";
    private static final String AVAILABILITY = "availability";
    private static final String PRODUCT_ID = "productId";

    /**
     * Execute and take all information from site and update it in database
     * Administrator only
     * @param manager
     * @param response
     * @return
     */
    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        Optional<String> productName = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_NAME));
        Optional<String> photo = Optional.ofNullable(requestContext.getRequestParameter(PHOTO));
        Optional<String> price = Optional.ofNullable(requestContext.getRequestParameter(PRICE));
        Optional<String> category = Optional.ofNullable(requestContext.getRequestParameter(CATEGORY));
        Optional<String> description = Optional.ofNullable(requestContext.getRequestParameter(DESCRIPTION));
        Optional<String> availability = Optional.ofNullable(requestContext.getRequestParameter(AVAILABILITY));
        Optional<String> promotion = Optional.ofNullable(requestContext.getRequestParameter(PROMOTION));
        Optional<String> productId = Optional.ofNullable(requestContext.getRequestParameter(PRODUCT_ID));

        try {
            ProductService productService = new ProductServiceImpl();
            int categoryId = 0;

            if (productName.isPresent() && photo.isPresent() && price.isPresent() && category.isPresent() &&
                    description.isPresent()) {
                boolean status = false;
                if (availability.isPresent()) {
                    status = true;
                }
                productService.update(productId.get(), productName.get(),
                        photo.get(), price.get(), category.get(), status, description.get(), promotion.get());
            }
            Optional<Product> product = productService.readById(Integer.parseInt(productId.get()));
            if (product.isPresent()) {
                categoryId = product.get().getCategoryId();
                manager.updateRequest(requestContext);
                return new CommandResult(PAGE + CATEGORY_PARAMETER + categoryId, CommandType.REDIRECT);
            }
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }
    }
}
