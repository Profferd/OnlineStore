package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRows implements RowAggregator<Role> {
    @Override
    public Role aggregate(ResultSet resultSet) throws SQLException {
        return new Role(resultSet.getInt("id"),
                resultSet.getString("role"));
    }
}
