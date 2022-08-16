package com.epam.hrushko.onlinestore.service.impl;

import com.epam.hrushko.onlinestore.dao.CategoryDao;
import com.epam.hrushko.onlinestore.dao.DaoFactory;
import com.epam.hrushko.onlinestore.dao.ProductDao;
import com.epam.hrushko.onlinestore.dao.PromotionDao;
import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.entity.Promotion;
import com.epam.hrushko.onlinestore.exceptions.DaoException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.ProductService;
import com.epam.hrushko.onlinestore.service.validate.IdValidator;
import com.epam.hrushko.onlinestore.service.validate.PriceValidator;
import com.epam.hrushko.onlinestore.service.validate.Validator;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {
    @Override
    public List<Product> readByCategory(int id) throws ServiceException {
        try {
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            List<Product> result = null;
            result = productDao.readByCategory(id);
            return result;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Product> readById(int id) throws ServiceException {
        try {
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            Optional<Product> result;
            result = productDao.readById(id);
            return result;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<Product> readFromOrders(List<Order> orders) throws ServiceException {
        List<Product> products = new LinkedList<>();

        for (Order order : orders) {
            Optional<Product> product = readById(order.getProductId());
            if (product.isPresent()) {
                if (!products.contains(product.get())) {
                    products.add(product.get());
                }
            }
        }
        return products;
    }

    @Override
    public boolean create(String productName, String photo, String priceString, String categoryName, boolean status, String description) throws ServiceException {
        if (productName == null || photo == null || categoryName == null || description == null) {
            return false;
        }

        Validator priceValidator = new PriceValidator();
        if (!priceValidator.isValid(priceString)) {
            return false;
        }

        try {
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            Optional<Product> productExist = productDao.readByName(productName);
            if (productExist.isPresent()) {
                return false;
            }
            int categoryId = getCategoryId(categoryName);
            double price = Double.parseDouble(priceString);
            Product product = new Product();
            product.setCategoryId(categoryId);
            product.setName(productName);
            product.setDescription(description);
            product.setPrice(price);
            product.setStatus(status);
            product.setPhoto(photo);
            productDao.create(product);

            return true;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean update(String productIdString, String productName, String photo, String priceString, String categoryName, boolean status, String description, String promotionIdString) throws ServiceException {
        if (productIdString == null || productName == null || photo == null || priceString == null ||
                categoryName == null || description == null || promotionIdString == null) {
            return false;
        }

        Validator priceValidator = new PriceValidator();
        if (!priceValidator.isValid(priceString)) {
            return false;
        }

        Validator idValidator = new IdValidator();
        if (!(idValidator.isValid(promotionIdString) || idValidator.isValid(productIdString))) {
            return false;
        }
        int categoryId = getCategoryId(categoryName);
        int promotionId = Integer.parseInt(promotionIdString);
        int productId = Integer.parseInt(productIdString);
        try {
            PromotionDao promotionDao = DaoFactory.getInstance().getPromotionDao();
            Optional<Promotion> promotion = promotionDao.readById(promotionId);
            if (!(promotion.isPresent() || promotionId == 0)) {
                return false;
            }
            double price = Double.parseDouble(priceString);
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            Product product = new Product();
            product.setCategoryId(categoryId);
            product.setName(productName);
            product.setDescription(description);
            product.setPrice(price);
            product.setStatus(status);
            product.setPhoto(photo);
            product.setPromotionId(promotionId);
            productDao.updateById(productId, product);
            if (promotionId == 0) {
                productDao.deletePromotion(productId);
            } else {
                productDao.updatePromotion(productId, promotionId);
            }

            return true;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage(), e);
        }
    }

    private int getCategoryId(String categoryName) throws ServiceException {
        try {
            CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
            Optional<Category> categoryExist = categoryDao.readByName(categoryName);

            int categoryId;
            if (categoryExist.isPresent()) {
                categoryId = categoryExist.get().getId();
            } else {
                Category category = new Category();
                category.setCategory(categoryName);
                categoryId = categoryDao.create(category);
            }
            return categoryId;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
