package com.epam.hrushko.onlinestore.dao.impl;

import com.epam.hrushko.onlinestore.dao.OrderDao;
import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.dao.aggregator.RowFactory;
import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public class OrderDaoImpl extends BaseDaoImpl implements OrderDao {
    private static final String READ = "SELECT * FROM `order`";
    private static final String READ_BY_ID = "SELECT * FROM `order` WHERE `id`=?";
    private static final String READ_BY_USER = "SELECT * FROM `order` WHERE user_id=? ORDER BY id ASC";
    private static final String READ_BY_ORDER = "SELECT * FROM `order` WHERE userOrder_id=?";
    private static final String READ_BY_USER_AND_PRODUCT = "SELECT * FROM `order` WHERE `product_id`=? AND `user_id`=?";
    private static final String CREATE = "INSERT INTO `order` (`product_id`, `user_id`, `number`) VALUES (?, ?, ?)";
    private static final String DELETE = "DELETE FROM `order` WHERE `id`=?";
    private static final String UPDATE = "UPDATE `order` SET `userOrder_id`=? WHERE `id`=?";


    public OrderDaoImpl() {
        super(RowFactory.getInstance().getOrderRows());
    }

    @Override
    public List<Order> read() throws DaoException {
        return read(READ);
    }

    @Override
    public int create(Order item) throws DaoException {
        return create(CREATE, item.getProductId(), item.getUserId(),
                item.getNumber());
    }

    @Override
    public void delete(int id) throws DaoException {
        update(DELETE, id);
    }

    @Override
    public void update(Order item) throws DaoException {
        update(UPDATE, item.getOrderId(), item.getId());
    }

    @Override
    public List<Order> readByUser(int userId) throws DaoException {
        return read(READ_BY_USER, userId);
    }

    @Override
    public List<Order> readByUserOrder(int userOrderId) throws DaoException {
        return read(READ_BY_ORDER, userOrderId);
    }

    @Override
    public List<Order> readUserAndProduct(int userId, int productId) throws DaoException {
        return read(READ_BY_USER_AND_PRODUCT, productId, userId);
    }

    @Override
    public Optional<Order> readById(int id) throws DaoException {
        return readForSingleResult(READ_BY_ID, id);
    }

    @Override
    public void update(int id, int userOrderId) throws DaoException {
        update(UPDATE, userOrderId, id);
    }
}
