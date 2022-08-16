package com.epam.hrushko.onlinestore.service;

import com.epam.hrushko.onlinestore.entity.Role;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.Optional;

public interface RoleService {

    Optional<Role> findById(int roleId) throws ServiceException;

}
