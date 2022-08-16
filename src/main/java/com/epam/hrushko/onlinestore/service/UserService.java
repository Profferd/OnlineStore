package com.epam.hrushko.onlinestore.service;

import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * User information interface
 */
public interface UserService {

    /**
     * Logining
     * @param email
     * @param password
     * @return successfully log in or not
     * @throws ServiceException
     */
    Optional<User> login(String email, String password) throws ServiceException;

    /**
     * Registration
     * @param name
     * @param surname
     * @param email
     * @param phone
     * @param password
     * @return successfully or not
     * @throws ServiceException
     */
    boolean register(String name, String surname, String email, String phone, String password) throws ServiceException;

    Optional<User> findUserById(int userId) throws ServiceException;

    List<User> getUser(List<Order> orders) throws ServiceException;

}
