package com.epam.hrushko.onlinestore.dao;

import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.Optional;

public interface CategoryDao extends Dao<Category> {

    Optional<Category> readById(int id) throws DaoException;

    Optional<Category> readByName(String name) throws DaoException;

}
