package com.epam.hrushko.onlinestore.service;

import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Product service interface
 */
public interface ProductService {

    /**
     * Find product by category id
     * @param id
     * @return all product
     * @throws ServiceException
     */
    List<Product> readByCategory(int id) throws ServiceException;

    /**
     * Find product by id
     * @param id
     * @return product
     * @throws ServiceException
     */
    Optional<Product> readById(int id) throws ServiceException;

    /**
     * Find all product by orders
     * @param orders
     * @return products
     * @throws ServiceException
     */
    List<Product> readFromOrders(List<Order> orders) throws ServiceException;

    /**
     * Create new product
     * @param productName
     * @param photo
     * @param priceString
     * @param categoryName
     * @param status
     * @param description
     * @return created or not
     * @throws ServiceException
     */
    boolean create(String productName, String photo, String priceString, String categoryName,
                          boolean status, String description) throws ServiceException;

    /**
     * Update product
     * @param productIdString
     * @param productName
     * @param photo
     * @param priceString
     * @param categoryName
     * @param status
     * @param description
     * @param promotionIdString
     * @return updated or not
     * @throws ServiceException
     */
    boolean update(String productIdString, String productName, String photo, String priceString, String categoryName,
                   boolean status, String description, String promotionIdString) throws ServiceException;

}
