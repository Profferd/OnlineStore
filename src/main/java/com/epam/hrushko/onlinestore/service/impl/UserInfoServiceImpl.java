package com.epam.hrushko.onlinestore.service.impl;

import com.epam.hrushko.onlinestore.dao.DaoFactory;
import com.epam.hrushko.onlinestore.dao.UserInfoDao;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.entity.UserInfo;
import com.epam.hrushko.onlinestore.exceptions.DaoException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.UserInfoService;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public Optional<UserInfo> readById(int userInfoId) throws ServiceException {
        try {
            UserInfoDao userInfoDao = DaoFactory.getInstance().getUserInfoDao();
            Optional<UserInfo> userInfo;
            userInfo = userInfoDao.read(userInfoId);
            return userInfo;
        } catch (DaoException e) {
            //logger
            throw new ServiceException(e.getMessage(), e);
        }
    }

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
            //logger
            throw new ServiceException(e.getMessage(), e);
        }
        return userInfos;
    }
}
