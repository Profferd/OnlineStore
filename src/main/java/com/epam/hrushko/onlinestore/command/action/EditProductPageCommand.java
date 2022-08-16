package com.epam.hrushko.onlinestore.command.action;

import com.epam.hrushko.onlinestore.command.Command;
import com.epam.hrushko.onlinestore.command.CommandResult;
import com.epam.hrushko.onlinestore.command.CommandType;
import com.epam.hrushko.onlinestore.command.request.RequestManager;
import com.epam.hrushko.onlinestore.command.request.Requests;
import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.CategoryService;
import com.epam.hrushko.onlinestore.service.ProductService;
import com.epam.hrushko.onlinestore.service.impl.CategoryServiceImpl;
import com.epam.hrushko.onlinestore.service.impl.ProductServiceImpl;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

public class EditProductPageCommand implements Command {
    private static final String PAGE = "WEB-INF/view/editProduct.jsp";
    private static final String ERROR_PAGE = "WEB-INF/view/error.jsp";
    private static final String CATEGORIES = "category";
    private static final String PRODUCT_ID = "productId";
    private static final String PRODUCT = "product";
    private static final String CATEGORY = "category";

    @Override
    public CommandResult execute(RequestManager manager, HttpServletResponse response) {
        Requests requestContext = manager.createContext();

        try {
            CategoryService categoryService = new CategoryServiceImpl();
            List<Category> categories = categoryService.readCategories();
            requestContext.addRequestAttribute(CATEGORIES, categories);

            int productId = Integer.parseInt(requestContext.getRequestParameter(PRODUCT_ID));
            ProductService productService = new ProductServiceImpl();
            Optional<Product> product = productService.readById(productId);
            if (product.isPresent()) {
                requestContext.addRequestAttribute(PRODUCT, product.get());
                Optional<Category> category = categoryService.readCategoryById(product.get().getCategoryId());
                if (category.isPresent()) {
                    requestContext.addRequestAttribute(PRODUCT, product.get());
                    requestContext.addRequestAttribute(CATEGORY, category.get());
                }
            }
        } catch (ServiceException e) {
            return new CommandResult(ERROR_PAGE, CommandType.FORWARD);
        }

        manager.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandType.FORWARD);
    }
}
