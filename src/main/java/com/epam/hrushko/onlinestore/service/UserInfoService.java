package com.epam.hrushko.onlinestore.service;

import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.entity.UserInfo;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * User information service interface
 */
public interface UserInfoService {

    /**
     * Find user information by id
     * @param userInfoId
     * @return user information
     * @throws ServiceException
     */
    Optional<UserInfo> readById(int userInfoId) throws ServiceException;

    /**
     * Find users information by users
     * @param users
     * @return all user info
     * @throws ServiceException
     */
    List<UserInfo> readFromUsers(List<User> users) throws ServiceException;

}
