package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.Promotion;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PromotionRows implements RowAggregator<Promotion> {
    @Override
    public Promotion aggregate(ResultSet resultSet) throws SQLException {
        return new Promotion(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("description"),
                resultSet.getDate("start_date"),
                resultSet.getDate("end_date"),
                resultSet.getString("photo"),
                resultSet.getByte("discount"));
    }
}
