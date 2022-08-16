package com.epam.hrushko.onlinestore.dao.impl;

import com.epam.hrushko.onlinestore.dao.UserInfoDao;
import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.dao.aggregator.RowFactory;
import com.epam.hrushko.onlinestore.entity.UserInfo;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public class UserInfoDaoImpl extends BaseDaoImpl implements UserInfoDao {
    private static final String CREATE = "INSERT INTO `userinfo` (`name`, `surname`, `phone`) VALUE (?, ?, ?)";
    private static final String READ = "SELECT * WHERE `id`=?";
    private static final String READ_BY_ID = "SELECT * FROM `userinfo` WHERE `id`=?";
    private static final String UPDATE = "UPDATE `userinfo` SET `name`=?, `surname`=?, `phone`=? WHERE `id`=?";
    private static final String DELETE = "DELETE FROM `userinfo` WHERE `id`=?";

    public UserInfoDaoImpl() {
        super(RowFactory.getInstance().getUserInfoRows());
    }

    @Override
    public List<UserInfo> read() throws DaoException {
        return read(READ);
    }

    @Override
    public int create(UserInfo item) throws DaoException {
        return create(CREATE, item.getName(),
                item.getSurname(), item.getPhone());
    }

    @Override
    public void delete(int id) throws DaoException {
        update(DELETE, id);
    }

    @Override
    public void update(UserInfo item) throws DaoException {
        update(UPDATE, item.getName(),
                item.getSurname(), item.getPhone(),
                item.getId());
    }

    @Override
    public Optional<UserInfo> read(int id) throws DaoException {
        return readForSingleResult(READ_BY_ID, id);
    }
}
