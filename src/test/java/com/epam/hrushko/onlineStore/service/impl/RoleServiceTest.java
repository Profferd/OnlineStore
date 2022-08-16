package com.epam.hrushko.onlineStore.service.impl;

import com.epam.hrushko.onlinestore.connection.ConnectionPool;
import com.epam.hrushko.onlinestore.entity.Role;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.exceptions.ConnectionException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.RoleService;
import com.epam.hrushko.onlinestore.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RoleServiceTest {
    RoleService roleService = new RoleServiceImpl();

    @BeforeAll
    static void initializeConnectionPool() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
    }

    @Test
    public void testLoginShouldReturnRole() throws ServiceException {
        Optional<Role> actual = roleService.findById(1);
        assertTrue(actual.isPresent());
    }

    @Test
    public void testLoginShouldReturnNull() throws ServiceException {
        Optional<Role> actual = roleService.findById(4);
        assertFalse(actual.isPresent());
    }
}
