package com.epam.hrushko.onlinestore.dao.aggregator;

import com.epam.hrushko.onlinestore.dao.aggregator.impl.*;
import com.epam.hrushko.onlinestore.entity.*;

public class RowFactory {
    private static RowFactory instance = null;
    private final RowAggregator<Category> categoryRowMapper = new CategoryRows();
    private final RowAggregator<Order> orderRowMapper = new OrderRows();
    private final RowAggregator<Product> productRowMapper = new ProductRows();
    private final RowAggregator<Promotion> promotionRowMapper = new PromotionRows();
    private final RowAggregator<Role> roleRowMapper = new RoleRows();
    private final RowAggregator<UserInfo> userInfoRowMapper = new UserInfoRows();
    private final RowAggregator<UserOrder> userOrderRowMapper = new UserOrderRows();
    private final RowAggregator<User> userRowMapper = new UserRows();

    public static RowFactory getInstance() {
        if (instance==null)
            instance = new RowFactory();
        return instance;
    }

    public RowAggregator<Category> getCategoryRows() {
        return categoryRowMapper;
    }

    public RowAggregator<Order> getOrderRows() {
        return orderRowMapper;
    }

    public RowAggregator<Product> getProductRows() {
        return productRowMapper;
    }

    public RowAggregator<Promotion> getPromotionRows() {
        return promotionRowMapper;
    }

    public RowAggregator<Role> getRoleRows() {
        return roleRowMapper;
    }

    public RowAggregator<UserInfo> getUserInfoRows() {
        return userInfoRowMapper;
    }

    public RowAggregator<UserOrder> getUserOrderRows() {
        return userOrderRowMapper;
    }

    public RowAggregator<User> getUserRows() {
        return userRowMapper;
    }

}
