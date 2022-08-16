package com.epam.hrushko.onlinestore.service;

import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.entity.UserOrder;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.List;

/**
 * Order service inteface
 */
public interface OrderService {

    /**
     * Find user by user id
     * @param userId
     * @return order
     * @throws ServiceException
     */
    List<Order> readByUser(int userId) throws ServiceException;

    /**
     * Find orders by user order id
     * @param userOrderId
     * @return all orders
     * @throws ServiceException
     */
    List<Order> readByUserOrder(int userOrderId) throws ServiceException;

    /**
     * Delete order by order id
     * @param orderId
     * @return true or false
     * @throws ServiceException
     */
    boolean delete(int orderId) throws ServiceException;

    /**
     * Create order
     * @param userId
     * @param productId
     * @param number
     * @return create or not
     * @throws ServiceException
     */
    boolean create(int userId, int productId, int number) throws ServiceException;

    /**
     * Calculating total cost of products
     * @param orders
     * @return total cost
     * @throws ServiceException
     */
    double calculateTotalCost(List<Order> orders) throws ServiceException;

    /**
     * Find all orders using user orders
     * @param userOrders
     * @return all orders
     * @throws ServiceException
     */
    List<Order> readOrders(List<UserOrder> userOrders) throws ServiceException;

}
