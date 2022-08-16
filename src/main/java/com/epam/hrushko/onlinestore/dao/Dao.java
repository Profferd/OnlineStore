package com.epam.hrushko.onlinestore.dao;

import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.List;

public interface Dao<T> {

    /**
     * Find T in database
     * @return list of T
     * @throws DaoException
     */
    List<T> read() throws DaoException;

    /**
     * Create new Entity
     * @param item
     * @return id of new created entity
     * @throws DaoException
     */
    int create(T item) throws DaoException;

    /**
     * Delete entity in database
     * @param id of deleted entity
     * @throws DaoException
     */
    void delete(int id) throws DaoException;

    /**
     * Update item in database
     * @param item which you want to update
     * @throws DaoException
     */
    void update(T item) throws DaoException;
}
