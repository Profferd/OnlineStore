package com.epam.hrushko.onlinestore.dao;

import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public interface ProductDao extends Dao<Product> {

    List<Product> readByCategory(int categoryId) throws DaoException;

    Optional<Product> readById(int id) throws DaoException;

    Optional<Product> readByName(String name) throws DaoException;

    void updateById(int id, Product product) throws DaoException;

    void updatePromotion(int id, int promotionId) throws DaoException;

    void deletePromotion(int id) throws DaoException;

}
