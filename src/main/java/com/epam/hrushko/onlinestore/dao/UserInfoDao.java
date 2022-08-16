package com.epam.hrushko.onlinestore.dao;

import com.epam.hrushko.onlinestore.entity.UserInfo;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.Optional;

public interface UserInfoDao extends Dao<UserInfo> {

    Optional<UserInfo> read(int id) throws DaoException;

}
