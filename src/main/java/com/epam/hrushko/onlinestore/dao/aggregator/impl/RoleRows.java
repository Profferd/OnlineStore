package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for mapping role rows
 */
public class RoleRows implements RowAggregator<Role> {
    /**
     * Mapping all rows of role entity
     * @param resultSet
     * @return role with all information
     * @throws SQLException
     */
    @Override
    public Role aggregate(ResultSet resultSet) throws SQLException {
        return new Role(resultSet.getInt("id"),
                resultSet.getString("role"));
    }
}
