package com.epam.hrushko.onlinestore.service.impl;

import com.epam.hrushko.onlinestore.dao.CategoryDao;
import com.epam.hrushko.onlinestore.dao.DaoFactory;
import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.exceptions.DaoException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.CategoryService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

/**
 * Category service
 */
public class CategoryServiceImpl implements CategoryService {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Output all categories
     * @return all categories
     * @throws ServiceException
     */
    @Override
    public List<Category> readCategories() throws ServiceException {
        try {
            CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
            List<Category> categories = null;
            categories = categoryDao.read();
            return categories;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Find category using category id
     * @param categoryId
     * @return category
     * @throws ServiceException
     */
    @Override
    public Optional<Category> readCategoryById(int categoryId) throws ServiceException {
        try {
            CategoryDao categoryDao = DaoFactory.getInstance().getCategoryDao();
            Optional<Category> category;
            category = categoryDao.readById(categoryId);
            return category;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }
}
