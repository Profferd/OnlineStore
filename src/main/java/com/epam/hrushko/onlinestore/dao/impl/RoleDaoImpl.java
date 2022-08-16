package com.epam.hrushko.onlinestore.dao.impl;

import com.epam.hrushko.onlinestore.dao.RoleDao;
import com.epam.hrushko.onlinestore.dao.aggregator.RowFactory;
import com.epam.hrushko.onlinestore.entity.Role;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDaoImpl extends BaseDaoImpl implements RoleDao {
    private static final String FIND_BY_ID = "SELECT * FROM `role` WHERE `id`=?";
    private static final String FIND_ALL = "SELECT * FROM `role`";
    private static final String CREATE = "INSERT INTO `role` (`role`) VALUES (?)";
    private static final String UPDATE = "UPDATE `role` SET `role`=? WHERE `id`=?";
    private static final String DELETE = "DELETE FROM `role` WHERE `id`=?";

    public RoleDaoImpl() {
        super(RowFactory.getInstance().getRoleRows());
    }

    @Override
    public Optional<Role> read(int id) throws DaoException {
        return readForSingleResult(FIND_BY_ID, id);
    }

    @Override
    public List<Role> read() throws DaoException {
        return read(FIND_ALL);
    }

    @Override
    public int create(Role item) throws DaoException {
        return create(CREATE, item.getName());
    }

    @Override
    public void delete(int id) throws DaoException {
        update(DELETE, id);
    }

    @Override
    public void update(Role item) throws DaoException {
        update(UPDATE, item.getName(), item.getId());
    }
}
