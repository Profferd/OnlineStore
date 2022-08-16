package com.epam.hrushko.onlinestore.dao.impl;

import com.epam.hrushko.onlinestore.dao.ProductDao;
import com.epam.hrushko.onlinestore.dao.PromotionDao;
import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.dao.aggregator.RowFactory;
import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.entity.Promotion;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public class PromotionDaoImpl extends BaseDaoImpl implements PromotionDao {
    private static final String READ = "SELECT * FROM `promotion`";
    private static final String READ_BY_ID = "SELECT * FROM `promotion` WHERE `id`=?";
    private static final String READ_BY_NAME = "SELECT * FROM `promotion` WHERE `name`=?";
    private static final String CREATE = "INSERT INTO `promotion` (`name`, `description`, `start_date`, `end_date`, `photo`, `discount`) VALUES(?, ?, ?, ?, ?, ?";
    private static final String DELETE = "DELETE FROM `promotion` WHERE `id`=?";
    private static final String UPDATE = "UPDATE `promotion` SET `name`=?, `description`=?, `start_date`=?, `end_date`=?, `photo`=?, `discount`=? WHERE `id`=?";

    public PromotionDaoImpl() {
        super(RowFactory.getInstance().getPromotionRows());
    }

    @Override
    public List<Promotion> read() throws DaoException {
        return read(READ);
    }

    @Override
    public int create(Promotion item) throws DaoException {
        return create(CREATE, item.getName(), item.getDescription(),
                item.getStartDate(), item.getEndDate(),
                item.getPhoto(), item.getDiscount());
    }

    @Override
    public void delete(int id) throws DaoException {
        update(DELETE, id);
    }

    @Override
    public void update(Promotion item) throws DaoException {
        update(UPDATE, item.getName(), item.getDescription(),
                item.getStartDate(), item.getEndDate(),
                item.getPhoto(), item.getDiscount(),
                item.getId());
    }

    @Override
    public Optional<Promotion> readById(int id) throws DaoException {
        return readForSingleResult(READ_BY_ID, id);
    }

    @Override
    public Optional<Promotion> readByName(String name) throws DaoException {
        return readForSingleResult(READ_BY_NAME, name);
    }
}
