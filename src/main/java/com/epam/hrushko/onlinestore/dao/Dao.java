package com.epam.hrushko.onlinestore.dao;

import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.List;

public interface Dao<T> {

    List<T> read() throws DaoException;

    int create(T item) throws DaoException;

    void delete(int id) throws DaoException;

    void update(T item) throws DaoException;
}
