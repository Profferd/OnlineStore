package com.epam.hrushko.onlinestore.service;

import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.entity.UserOrder;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.List;

public interface OrderService {


    List<Order> readByUser(int userId) throws ServiceException;

    List<Order> readByUserOrder(int userOrderId) throws ServiceException;

    boolean delete(int orderId) throws ServiceException;

    boolean create(int userId, int productId, int number) throws ServiceException;

    double calculateTotalCost(List<Order> orders) throws ServiceException;

    List<Order> readOrders(List<UserOrder> userOrders) throws ServiceException;

}
