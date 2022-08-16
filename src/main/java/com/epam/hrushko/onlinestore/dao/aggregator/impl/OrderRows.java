package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderRows implements RowAggregator<Order> {
    @Override
    public Order aggregate(ResultSet resultSet) throws SQLException {
        return new Order(resultSet.getInt("id"),
                resultSet.getInt("product_id"),
                resultSet.getInt("user_id"),
                resultSet.getInt("userOrder_id"),
                resultSet.getInt("number"));
    }
}
