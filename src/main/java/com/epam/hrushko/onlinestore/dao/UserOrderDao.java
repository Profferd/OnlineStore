package com.epam.hrushko.onlinestore.dao;

import com.epam.hrushko.onlinestore.entity.UserOrder;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface UserOrderDao extends Dao<UserOrder> {

    List<UserOrder> findByStatus(String status) throws DaoException;

    Optional<UserOrder> findById(int id) throws DaoException;

    void updateStatus(int id, String status) throws DaoException;

}
