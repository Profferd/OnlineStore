package com.epam.hrushko.onlinestore.dao.aggregator.impl;

import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.entity.Promotion;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for mapping promotion rows
 */
public class PromotionRows implements RowAggregator<Promotion> {
    /**
     * Mapping all rows of promotion entity
     * @param resultSet
     * @return promotion with all information
     * @throws SQLException
     */
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
