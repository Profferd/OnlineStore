package com.epam.hrushko.onlinestore.service.impl;

import com.epam.hrushko.onlinestore.dao.DaoFactory;
import com.epam.hrushko.onlinestore.dao.RoleDao;
import com.epam.hrushko.onlinestore.entity.Role;
import com.epam.hrushko.onlinestore.exceptions.DaoException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.RoleService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Optional;

public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public Optional<Role> findById(int roleId) throws ServiceException {
        try {
            RoleDao roleDao = DaoFactory.getInstance().getRoleDao();
            Optional<Role> ans;
            ans = roleDao.read(roleId);
            return ans;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, "Cannot find role by id");
            throw new ServiceException(e.getMessage(), e);
        }
    }
}