package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.UserOrder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserOrderRows implements RowAggregator<UserOrder> {
    @Override
    public UserOrder aggregate(ResultSet resultSet) throws SQLException {
        return new UserOrder(resultSet.getInt("id"),
                resultSet.getString("address"),
                resultSet.getDate("order_date"),
                resultSet.getDate("delivery_date"),
                resultSet.getString("status")
                );
    }
}
