package com.epam.hrushko.onlineStore.service.impl;

import com.epam.hrushko.onlinestore.connection.ConnectionPool;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.entity.UserInfo;
import com.epam.hrushko.onlinestore.exceptions.ConnectionException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.UserInfoService;
import com.epam.hrushko.onlinestore.service.UserService;
import com.epam.hrushko.onlinestore.service.impl.UserInfoServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class UserInfoServiceTest {
    UserInfoService userInfoService = new UserInfoServiceImpl();

    @BeforeAll
    static void initializeConnectionPool() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
    }

    @Test
    public void testLoginShouldReturnUserInfo() throws ServiceException {
        Optional<UserInfo> actual = userInfoService.readById(1);
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Vasya");
        userInfo.setSurname("Romanovich");
        userInfo.setPhone(380123123);
        userInfo.setId(1);
        Optional<UserInfo> expected = Optional.of(userInfo);
        assertEquals(expected, actual);
    }

    @Test
    public void testLoginShouldReturnNull() throws ServiceException {
        Optional<UserInfo> actual = userInfoService.readById(7);
        assertFalse(actual.isPresent());
    }

    @Test
    public void testLoginShouldReturnNull1() throws ServiceException {
        User user = new User();
        user.setEmail("asdw@gmail.com");
        user.setPassword("1313");
        user.setUserInfoId(1);
        user.setRoleId(1);
        user.setId(1);
        User user1 = new User();
        user1.setEmail("admin@admin.com");
        user1.setPassword("1");
        user1.setUserInfoId(4);
        user1.setRoleId(2);
        user1.setId(4);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        List<UserInfo> expected = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Vasya");
        userInfo.setSurname("Romanovich");
        userInfo.setPhone(380123123);
        userInfo.setId(1);
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setName("Admin");
        userInfo1.setSurname("Admin1");
        userInfo1.setPhone(123456789);
        userInfo1.setId(4);
        expected.add(userInfo);
        expected.add(userInfo1);
        List<UserInfo> actual = userInfoService.readFromUsers(users);
        assertNotEquals(expected, actual);
    }

    @Test
    public void testFindUserById() throws ServiceException {
        User user = new User();
        user.setEmail("asdw@gmail.com");
        user.setPassword("1313");
        user.setUserInfoId(1);
        user.setRoleId(1);
        user.setId(1);
        User user1 = new User();
        user1.setEmail("admin@admin.com");
        user1.setPassword("1");
        user1.setUserInfoId(4);
        user1.setRoleId(2);
        user1.setId(4);
        List<User> users = new ArrayList<>();
        users.add(user);
        users.add(user1);
        List<UserInfo> expected = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        userInfo.setName("Vasya");
        userInfo.setSurname("Romanovich");
        userInfo.setPhone(380123123);
        userInfo.setId(1);
        UserInfo userInfo1 = new UserInfo();
        userInfo1.setName("Admin");
        userInfo1.setSurname("Admin");
        userInfo1.setPhone(123456789);
        userInfo1.setId(4);
        expected.add(userInfo);
        expected.add(userInfo1);
        List<UserInfo> actual = userInfoService.readFromUsers(users);
        assertEquals(expected, actual);
    }
}
