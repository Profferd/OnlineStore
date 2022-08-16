package com.epam.hrushko.onlineStore.service.impl;

import com.epam.hrushko.onlinestore.connection.ConnectionPool;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.exceptions.ConnectionException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.UserService;
import com.epam.hrushko.onlinestore.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {
    UserService userService = new UserServiceImpl();

    @BeforeAll
    static void initializeConnectionPool() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
    }

    @Test
    public void testLoginShouldReturnUser() throws ServiceException {
        Optional<User> actual = userService.login("asdw@gmail.com", "1313");
        assertTrue(actual.isPresent());
    }

    @Test
    public void testLoginShouldReturnNull() throws ServiceException {
        Optional<User> actual = userService.login("asdw@gmail.com", "1312");
        assertFalse(actual.isPresent());
    }

    @Test
    public void testLoginShouldReturnNull1() throws ServiceException {
        Optional<User> actual = userService.login(null, null);
        assertFalse(actual.isPresent());
    }

    @Test
    public void testFindUserById() throws ServiceException {
        User user = new User();
        user.setEmail("asdw@gmail.com");
        user.setPassword("1313");
        user.setUserInfoId(1);
        user.setRoleId(1);
        user.setId(1);
        Optional<User> expected = Optional.of(user);
        Optional<User> actual = userService.findUserById(1);
        assertEquals(expected, actual);
    }

}
