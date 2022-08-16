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
import com.epam.hrushko.onlinestore.service.PromotionService;
import com.epam.hrushko.onlinestore.service.impl.CategoryServiceImpl;
import com.epam.hrushko.onlinestore.service.impl.ProductServiceImpl;
import com.epam.hrushko.onlinestore.service.impl.PromotionServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Class that show catalog page
 */
public class CatalogPageCommand implements Command {
    private static final String PAGE = "WEB-INF/view/catalog.jsp";
    private static final String ERROR = "WEB-INF/view/error.jsp";
    private static final String CATEGORY_ID = "categoryId";
    private static final String CATEGORIES = "category";
    private static final String PRODUCTS = "product";
    private static final String NEW_PRICE = "newPrice";
    private static final String PAGES = "pages";
    private static final String CUR_PAGE = "curPage";
    private static final int PRODUCT_BY_PAGE = 1;

    /**
     * Executes catalog page and show it
     * Also shows all product that exist in catalog
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
            int categoryId = Integer.parseInt(requestContext.getRequestParameter(CATEGORY_ID));

//            int temp = categoryId;
//
//            int pageNum = 1;
//            int recordByPage = 1;
//            if (requestContext.getRequestParameter("pages") != null) {
//                pageNum = Integer.parseInt(requestContext.getRequestParameter("pages"));
//            }


            ProductService productService = new ProductServiceImpl();
            List<Product> products = productService.readByCategory(categoryId);
//            List<Product> productList = new ArrayList<>();
//            for (int i = ((pageNum - 1) * PRODUCT_BY_PAGE); i < pageNum * PRODUCT_BY_PAGE; i++) {
//                productList.add(products.get(i));
//            }
//
//            int noOfPages = (int) Math.ceil(products.size() * 1.0 / recordByPage);
//
//            requestContext.addRequestAttribute(CUR_PAGE, pageNum);
//            requestContext.addRequestAttribute(PAGES, noOfPages);
            requestContext.addRequestAttribute(PRODUCTS, products);

            PromotionService promotionService = new PromotionServiceImpl();
            Map<String, Double> newPrices = promotionService.getNewPrices(products);
            requestContext.addRequestAttribute(NEW_PRICE, newPrices);
        } catch (ServiceException e) {
            return new CommandResult(ERROR, CommandType.FORWARD);
        }

        manager.updateRequest(requestContext);
        return new CommandResult(PAGE, CommandType.FORWARD);
    }
}
