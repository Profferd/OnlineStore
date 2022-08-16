package com.epam.hrushko.onlinestore.service.impl;

import com.epam.hrushko.onlinestore.dao.CategoryDao;
import com.epam.hrushko.onlinestore.dao.DaoFactory;
import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.exceptions.DaoException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.CategoryService;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public List<Category> readCategories() throws ServiceException {
        try {
            CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
            List<Category> categories = null;
            categories = categoryDao.read();
            return categories;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Category> readCategoryById(int categoryId) throws ServiceException {
        try {
            CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
            Optional<Category> category;
            category = categoryDao.readById(categoryId);
            return category;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
