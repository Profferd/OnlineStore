package com.epam.hrushko.onlinestore.dao;

import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface OrderDao extends Dao<Order> {

    List<Order> readByUser(int userId) throws DaoException;

    List<Order> readByUserOrder(int userOrderId) throws DaoException;

    List<Order> readUserAndProduct(int userId, int productId) throws DaoException;

    Optional<Order> readById(int id) throws DaoException;

    public void update(int id, int userOrderId) throws DaoException;

}
