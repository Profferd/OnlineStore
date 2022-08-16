package com.epam.hrushko.onlinestore.dao.impl;

import com.epam.hrushko.onlinestore.dao.ProductDao;
import com.epam.hrushko.onlinestore.dao.aggregator.RowAggregator;
import com.epam.hrushko.onlinestore.dao.aggregator.RowFactory;
import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.exceptions.DaoException;

import java.util.List;
import java.util.Optional;

public class ProductDaoImpl extends BaseDaoImpl implements ProductDao {
    private static final String READ = "SELECT * FROM `product`";
    private static final String READ_BY_CATEGORY = "SELECT * FROM `product` WHERE `category_id`=?";
    private static final String READ_BY_NAME = "SELECT * FROM `product` WHERE `name`=?";
    private static final String READ_BY_ID = "SELECT * FROM `product` WHERE `id`=?";
    private static final String DELETE = "DELETE FROM `product` WHERE `id`=?";
    private static final String CREATE = "INSERT INTO `product` (`category_id`, `name`, " +
            "`description`, `price`, `status`, `photo`, `orderNumber`) VALUE(?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE `product` SET `category_id`=?, `name`=?, " +
            "`description`=?, `price`=?, `status`=?, `photo`=?, `orderNumber`=? WHERE `id`=?";
    private static final String UPDATE1 = "UPDATE `product` SET `category_id`=?, `name`=?, " +
            "`description`=?, `price`=?, `status`=?, `photo`=? WHERE `id`=?";
    private static final String UPDATE_PROMOTION = "UPDATE `product` SET `promotion_id`=? WHERE `id`=?";
    private static final String DELETE_PROMOTION = "UPDATE `product` SET `promotion_id`=NULL WHERE `id`=?";

    public ProductDaoImpl() {
        super(RowFactory.getInstance().getProductRows());
    }

    @Override
    public List<Product> read() throws DaoException {
        return read(READ);
    }

    @Override
    public int create(Product item) throws DaoException {
        return create(CREATE, item.getCategoryId(), item.getName(),
                item.getDescription(), item.getPrice(), item.isStatus(),
                item.getPhoto(), item.getOrderNumber());
    }

    @Override
    public void delete(int id) throws DaoException {
        update(DELETE, id);
    }

    @Override
    public void update(Product item) throws DaoException {
        update(UPDATE, item.getCategoryId(), item.getName(),
                item.getDescription(), item.getPrice(), item.isStatus(),
                item.getPhoto(), item.getOrderNumber(), item.getId());
    }

    @Override
    public List<Product> readByCategory(int categoryId) throws DaoException {
        return read(READ_BY_CATEGORY, categoryId);
    }

    @Override
    public Optional<Product> readById(int id) throws DaoException {
        return readForSingleResult(READ_BY_ID, id);
    }

    @Override
    public Optional<Product> readByName(String name) throws DaoException {
        return readForSingleResult(READ_BY_NAME, name);
    }

    @Override
    public void updateById(int id, Product product) throws DaoException {
        update(UPDATE1, product.getCategoryId(), product.getName(),
                product.getDescription(), product.getPrice(),
                product.isStatus(), product.getPhoto(), id);
    }

    @Override
    public void updatePromotion(int id, int promotionId) throws DaoException {
        update(UPDATE_PROMOTION, promotionId, id);
    }

    @Override
    public void deletePromotion(int id) throws DaoException {
        update(DELETE_PROMOTION, id);
    }
}
