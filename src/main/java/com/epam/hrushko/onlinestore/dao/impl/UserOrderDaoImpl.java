package com.epam.hrushko.onlinestore.dao.impl;

import com.epam.hrushko.onlinestore.dao.UserOrderDao;
import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.dao.aggregator.RowFactory;
import com.epam.hrushko.onlinestore.entity.UserOrder;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public class UserOrderDaoImpl extends BaseDaoImpl implements UserOrderDao {
    private static final String READ = "SELECT * FROM `userOrder`";
    private static final String READ_BY_STATUS = "SELECT * FROM `userOrder` WHERE `status`=?";
    private static final String READ_BY_ID = "SELECT * FROM `userOrder` WHERE `id`=?";
    private static final String CREATE = "INSERT INTO `userOrder` (`address`, `order_date`, `delivery_date`, `status`) VALUE (?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE `userOrder` SET `status`=? WHERE `id`=?";
    private static final String DELETE = "DELETE FROM `userOrder` WHERE `id`=?";

    public UserOrderDaoImpl() {
        super(RowFactory.getInstance().getUserOrderRows());
    }

    @Override
    public List<UserOrder> read() throws DaoException {
        return read(READ);
    }

    @Override
    public int create(UserOrder item) throws DaoException {
        return create(CREATE, item.getAddress(), item.getOrderDate(),
                item.getDeliveryDate(), item.getStatus());
    }

    @Override
    public void delete(int id) throws DaoException {
        update(DELETE, id);
    }

    @Override
    public void update(UserOrder item) throws DaoException {
        update(UPDATE, item.getAddress(), item.getOrderDate(),
                item.getDeliveryDate(), item.getStatus(), item.getId());
    }

    @Override
    public List<UserOrder> findByStatus(String status) throws DaoException {
        return read(READ_BY_STATUS, status);
    }

    @Override
    public Optional<UserOrder> findById(int id) throws DaoException {
        return readForSingleResult(READ_BY_ID, id);
    }

    @Override
    public void updateStatus(int id, String status) throws DaoException {
        update(UPDATE, status, id);
    }
}
