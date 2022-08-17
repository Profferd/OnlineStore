package com.epam.hrushko.onlinestore.service.impl;

import com.epam.hrushko.onlinestore.dao.DaoFactory;
import com.epam.hrushko.onlinestore.dao.UserInfoDao;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.entity.UserInfo;
import com.epam.hrushko.onlinestore.exceptions.DaoException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.UserInfoService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * User information service class
 */
public class UserInfoServiceImpl implements UserInfoService {
    private static final Logger LOGGER = Logger.getLogger(UserInfoServiceImpl.class);

    /**
     * Find user information by id
     * @param userInfoId
     * @return user information
     * @throws ServiceException
     */
    @Override
    public Optional<UserInfo> readById(int userInfoId) throws ServiceException {
        try {
            UserInfoDao userInfoDao = DaoFactory.getInstance().getUserInfoDao();
            Optional<UserInfo> userInfo;
            userInfo = userInfoDao.read(userInfoId);
            return userInfo;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Find users information by users
     * @param users
     * @return all user info
     * @throws ServiceException
     */
    @Override
    public List<UserInfo> readFromUsers(List<User> users) throws ServiceException {
        List<UserInfo> userInfos = new LinkedList<>();
        try {
            for (User user:
                 users) {
                Optional<UserInfo> userInfo = readById(user.getUserInfoId());
                if (userInfo.isPresent()) {
                    if (!userInfos.contains(userInfo.get())) {
                        userInfos.add(userInfo.get());
                    }
                }
            }
        } catch (ServiceException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
        return userInfos;
    }
}
