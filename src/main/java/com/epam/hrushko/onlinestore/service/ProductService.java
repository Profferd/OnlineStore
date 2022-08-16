package com.epam.hrushko.onlinestore.service;


import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> readByCategory(int id) throws ServiceException;

    Optional<Product> readById(int id) throws ServiceException;

    List<Product> readFromOrders(List<Order> orders) throws ServiceException;

    boolean create(String productName, String photo, String priceString, String categoryName,
                          boolean status, String description) throws ServiceException;

    boolean update(String productIdString, String productName, String photo, String priceString, String categoryName,
                   boolean status, String description, String promotionIdString) throws ServiceException;

}
