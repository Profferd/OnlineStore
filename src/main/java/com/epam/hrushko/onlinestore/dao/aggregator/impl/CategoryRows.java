package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryRows implements RowAggregator<Category> {
    @Override
    public Category aggregate(ResultSet resultSet) throws SQLException {
        return new Category(resultSet.getInt("id"),
                resultSet.getString("category"));
    }
}
