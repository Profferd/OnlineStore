package com.epam.hrushko.onlinestore.service;

import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> readCategories() throws ServiceException;

    Optional<Category> readCategoryById(int categoryId) throws ServiceException;

}
