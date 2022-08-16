package com.epam.hrushko.onlinestore.dao.impl;

import com.epam.hrushko.onlinestore.connection.ConnectionPool;
import com.epam.hrushko.onlinestore.connection.MyConnection;
import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.exceptions.ConnectionException;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

abstract public class BaseDaoImpl<T> {

    protected Connection connection;

    private final RowAggregator<T> rowAggregator;

    public BaseDaoImpl(RowAggregator<T> rowAggregator) {
        this.rowAggregator = rowAggregator;
    }

    protected List<T> read(String query, Object... params) throws DaoException {
        List<T> entities = new ArrayList<>();
        try (PreparedStatement statement = createStatement(query, params);
             ResultSet resultSet = statement.executeQuery()) {
            entities = createList(resultSet);
        } catch (SQLException e) {
            //logger
            throw new DaoException(e.getMessage(), e);
        }
        return entities;
    }

    protected Optional<T> readForSingleResult(String query, Object... params) throws DaoException {
        List<T> items = read(query, params);
        if (items.isEmpty()) {
            return Optional.empty();
        }

        if (!(items.size() == 1)) {
            return Optional.empty();
        }

        return Optional.of(items.get(0));
    }

    protected int create(String query, Object... params) throws DaoException {
        int ans = 0;
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
            ResultSet generatedKey = statement.getGeneratedKeys();
            if (generatedKey.next()) {
                ans = generatedKey.getInt(1);
            }
        } catch (SQLException e) {
            //logger
            throw new DaoException(e.getMessage(), e);
        }
        return ans;
    }

    protected void update(String query, Object... params) throws DaoException {
        try (PreparedStatement statement = createStatement(query, params)) {
            statement.executeUpdate();
        } catch (SQLException e) {
            //logger
            throw new DaoException(e.getMessage(), e);
        }
    }

    private PreparedStatement createStatement(String query, Object[] params) throws DaoException {
        try {
            MyConnection connection = ConnectionPool.getInstance().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
            ConnectionPool.getInstance().releaseConnection(connection);
            return preparedStatement;
        } catch (SQLException | ConnectionException e) {
            //logger
            throw new DaoException(e.getMessage(), e);
        }
    }

    private List<T> createList(ResultSet resultSet) throws DaoException {
        List<T> entities = new ArrayList<>();
        try {
            while (resultSet.next()) {
                T entity = rowAggregator.aggregate(resultSet);
                entities.add(entity);
            }
        } catch (SQLException e) {
            //logger
            throw new DaoException(e.getMessage(), e);
        }
        return entities;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
