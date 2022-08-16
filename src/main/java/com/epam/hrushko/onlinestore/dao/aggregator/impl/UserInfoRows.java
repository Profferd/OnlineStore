package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.UserInfo;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserInfoRows implements RowAggregator<UserInfo> {
    @Override
    public UserInfo aggregate(ResultSet resultSet) throws SQLException {
        return new UserInfo(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("surname"),
                resultSet.getLong("phone"));
    }
}
