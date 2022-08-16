package com.epam.hrushko.onlinestore.dao;

import com.epam.hrushko.onlinestore.entity.Promotion;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.Optional;

public interface PromotionDao extends Dao<Promotion> {

    Optional<Promotion> readById(int id) throws DaoException;

    Optional<Promotion> readByName(String name) throws DaoException;
}
