package com.epam.hrushko.onlinestore.dao.aggregator;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowAggregator<T> {

    T aggregate(ResultSet resultSet) throws SQLException;

}
