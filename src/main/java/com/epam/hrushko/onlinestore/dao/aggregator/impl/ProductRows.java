package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for mapping product rows
 */
public class ProductRows implements RowAggregator<Product> {
    /**
     * Mapping all rows of product entity
     * @param resultSet
     * @return product with all information
     * @throws SQLException
     */
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
