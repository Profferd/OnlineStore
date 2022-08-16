package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.Order;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for mapping order rows
 */
public class OrderRows implements RowAggregator<Order> {
    /**
     * Mapping all rows of order entity
     * @param resultSet
     * @return order with all information
     * @throws SQLException
     */
    @Override
    public Order aggregate(ResultSet resultSet) throws SQLException {
        return new Order(resultSet.getInt("id"),
                resultSet.getInt("product_id"),
                resultSet.getInt("user_id"),
                resultSet.getInt("userOrder_id"),
                resultSet.getInt("number"));
    }
}
