package com.epam.hrushko.onlinestore.dao;

import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.Optional;

public interface UserDao extends Dao<User> {

    Optional<User> findByEmail(String email) throws DaoException;

    Optional<User> findByEmailAndPassword(String email, String password) throws DaoException;

    Optional<User> findById(int userId) throws DaoException;

}
