package com.epam.hrushko.onlinestore.service;

import com.epam.hrushko.onlinestore.entity.Role;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.Optional;

/**
 * Role service interface
 */
public interface RoleService {

    /**
     * Find Role by od
     * @param roleId
     * @return role
     * @throws ServiceException
     */
    Optional<Role> findById(int roleId) throws ServiceException;

}
