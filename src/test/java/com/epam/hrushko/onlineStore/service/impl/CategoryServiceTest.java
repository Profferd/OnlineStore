package com.epam.hrushko.onlineStore.service.impl;

import com.epam.hrushko.onlinestore.connection.ConnectionPool;
import com.epam.hrushko.onlinestore.entity.Category;
import com.epam.hrushko.onlinestore.exceptions.ConnectionException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.CategoryService;
import com.epam.hrushko.onlinestore.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CategoryServiceTest {
    CategoryService categoryService = new CategoryServiceImpl();

    @BeforeAll
    static void initializeConnectionPool() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
    }

    @Test
    public void testRetrieveCategoryBtId_ShouldReturnCategory_WhenDataIsCorrect() throws ServiceException {
        Optional<Category> actual = categoryService.readCategoryById(2);
        assertTrue(actual.isPresent());
    }

    @Test
    public void testRetrieveCategoryBtId_ShouldReturnNull_WhenDataIsNotCorrect() throws ServiceException {
        Optional<Category> actual = categoryService.readCategoryById(4);
        assertFalse(actual.isPresent());
    }

    @Test
    public void testRetrieveCategories_ShouldReturnCategories_WhenDataIsNotNull() throws ServiceException {
        List<Category> actual = categoryService.readCategories();

        Category categoryFirst = new Category();
        categoryFirst.setId(1);
        categoryFirst.setCategory("Laptop");

        Category categorySecond = new Category();
        categorySecond.setId(2);
        categorySecond.setCategory("Headset");

        Category categoryThree = new Category();
        categoryThree.setId(3);
        categoryThree.setCategory("TestCat");

        List<Category> expected = new LinkedList<>();
        expected.add(categorySecond);
        expected.add(categoryFirst);
        expected.add(categoryThree);

        assertEquals(expected, actual);
    }
}
