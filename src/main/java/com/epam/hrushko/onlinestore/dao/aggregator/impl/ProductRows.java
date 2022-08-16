package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRows implements RowAggregator<Product> {
    @Override
    public Product aggregate(ResultSet resultSet) throws SQLException {
        return new Product(resultSet.getInt("id"),
                resultSet.getInt("category_id"),
                resultSet.getInt("promotion_id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDouble("price"),
                resultSet.getBoolean("status"),
                resultSet.getString("photo"),
                resultSet.getInt("orderNumber"));
    }
}
