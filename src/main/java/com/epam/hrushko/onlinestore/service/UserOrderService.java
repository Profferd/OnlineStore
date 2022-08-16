package com.epam.hrushko.onlinestore.service;

import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.entity.UserOrder;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * User information order interface
 */
public interface UserOrderService {

    Optional<UserOrder> readById(int userOrderId) throws ServiceException;

    List<UserOrder> readByStatus(String status) throws ServiceException;

    boolean updateStatusById(int userOrderId, String status) throws ServiceException;

    boolean create(List<Order> orders, String address, String deliveryDateString, double totalPrice) throws ServiceException;

    List<UserOrder> getUserOrdersFromOrders(List<Order> orders) throws ServiceException;
}
