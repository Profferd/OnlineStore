package com.epam.hrushko.onlinestore.service;

import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Category service interface
 */
public interface CategoryService {

    /**
     * Output all categories
     * @return all categories
     * @throws ServiceException
     */
    List<Category> readCategories() throws ServiceException;

    /**
     * Find category using category id
     * @param categoryId
     * @return category
     * @throws ServiceException
     */
    Optional<Category> readCategoryById(int categoryId) throws ServiceException;

}
