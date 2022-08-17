package com.epam.hrushko.onlinestore.service.impl;

import com.epam.hrushko.onlinestore.dao.DaoFactory;
import com.epam.hrushko.onlinestore.dao.RoleDao;
import com.epam.hrushko.onlinestore.entity.Role;
import com.epam.hrushko.onlinestore.exceptions.DaoException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.RoleService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.Optional;

/**
 * Role service class
 */
public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = Logger.getLogger(RoleServiceImpl.class);

    /**
     * Find Role by od
     * @param roleId
     * @return role
     * @throws ServiceException
     */
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
