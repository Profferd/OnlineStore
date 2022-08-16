package com.epam.hrushko.onlinestore.service;

import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.entity.UserInfo;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.List;
import java.util.Optional;

public interface UserInfoService {

    Optional<UserInfo> readById(int userInfoId) throws ServiceException;

    List<UserInfo> readFromUsers(List<User> users) throws ServiceException;

}
