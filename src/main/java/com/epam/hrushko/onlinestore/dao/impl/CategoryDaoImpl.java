package com.epam.hrushko.onlinestore.dao.impl;

import com.epam.hrushko.onlinestore.dao.CategoryDao;
import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.dao.aggregator.RowFactory;
import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {
    private static final String READ = "SELECT * FROM `category`";
    private static final String READ_BY_ID = "SELECT * FROM `category` WHERE `id`=?";
    private static final String READ_BY_NAME = "SELECT * FROM `category` WHERE `category`=?";
    private static final String CREATE = "INSERT INTO `category` (`category`) VALUE (?)";
    private static final String DELETE = "DELETE FROM `category` WHERE `id`=?";
    private static final String UPDATE = "UPDATE `category` SET `category`=? WHERE `id`=?";
    public CategoryDaoImpl() {
        super(RowFactory.getInstance().getCategoryRows());
    }

    @Override
    public Optional<Category> readById(int id) throws DaoException {
        return readForSingleResult(READ_BY_ID, id);
    }

    @Override
    public Optional<Category> readByName(String name) throws DaoException {
        return readForSingleResult(READ_BY_NAME, name);
    }

    @Override
    public List<Category> read() throws DaoException {
        return read(READ);
    }

    @Override
    public int create(Category item) throws DaoException {
        return create(CREATE, item.getCategory());
    }

    @Override
    public void delete(int id) throws DaoException {
        update(DELETE, id);
    }

    @Override
    public void update(Category item) throws DaoException {
        update(UPDATE, item.getCategory(), item.getId());
    }
}
