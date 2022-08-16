package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for mapping user information rows
 */
public class UserInfoRows implements RowAggregator<UserInfo> {
    /**
     * Mapping all rows of user information entity
     * @param resultSet
     * @return user information with all information
     * @throws SQLException
     */
    @Override
    public UserInfo aggregate(ResultSet resultSet) throws SQLException {
        return new UserInfo(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getLong("phone"));
    }
}
