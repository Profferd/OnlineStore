package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRows implements RowAggregator<User> {
    @Override
    public User aggregate(ResultSet resultSet) throws SQLException {
        return new User(resultSet.getInt("id"),
                resultSet.getInt("userInfo_id"),
                resultSet.getInt("role_id"),
                resultSet.getString("email"),
                resultSet.getString("password")
                );
    }
}
