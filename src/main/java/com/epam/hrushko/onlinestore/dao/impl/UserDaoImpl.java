package com.epam.hrushko.onlinestore.dao.impl;

import com.epam.hrushko.onlinestore.dao.UserDao;
import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.dao.aggregator.RowFactory;
import com.epam.hrushko.onlinestore.entity.User;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    private static final String FIND_ALL = "SELECT * FROM `user` ORDER BY `email`";
    private static final String READ_BY_ID = "SELECT * FROM `user` WHERE `id`=?";
    private static final String FIND_BY_EMAIL = "SELECT * FROM `user` WHERE `email` = ?";
    private static final String FIND_BY_EMAIL_AND_PASSWORD = "SELECT * FROM `user` WHERE `email` = ? AND `password` = ?";
    private static final String CREATE = "INSERT INTO `user` (`userInfo_id`, `role_id`, `email`, `password`) VALUE (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE `user` SET `userInfo_id` = ?, `role_id` = ?, `email` = ?, `password` = ? WHERE `id` = ?";
    private static final String DELETE = "DELETE FROM `user` WHERE `id` = ?";

    public UserDaoImpl() {
        super(RowFactory.getInstance().getUserRows());
    }

    @Override
    public List<User> read() throws DaoException {
        return read(FIND_ALL);
    }

    @Override
    public int create(User item) throws DaoException {
       return create(CREATE, item.getUserInfoId(),
               item.getRoleId(), item.getEmail(),
               item.getPassword());
    }

    @Override
    public void delete(int id) throws DaoException {
        update(DELETE, id);
    }

    @Override
    public void update(User item) throws DaoException {
        update(UPDATE, item.getUserInfoId(),
                item.getRoleId(), item.getEmail(),
                item.getPassword(), item.getId());
    }

    @Override
    public Optional<User> findByEmail(String email) throws DaoException {
        return readForSingleResult(FIND_BY_EMAIL, email);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) throws DaoException {
        return readForSingleResult(FIND_BY_EMAIL_AND_PASSWORD, email, password);
    }

    @Override
    public Optional<User> findById(int userId) throws DaoException {
        return readForSingleResult(READ_BY_ID, userId);
    }
}
