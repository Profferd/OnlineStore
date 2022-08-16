package com.epam.hrushko.onlinestore.dao;

import com.epam.hrushko.onlinestore.entity.Role;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.Optional;

public interface RoleDao extends Dao<Role> {

    Optional<Role> read(int id) throws DaoException;

}
