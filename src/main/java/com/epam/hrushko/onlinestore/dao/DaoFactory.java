package com.epam.hrushko.onlinestore.dao;

import com.epam.hrushko.onlinestore.dao.impl.*;

/**
 * DAO factory.
 */
public class DaoFactory {
    private final UserDao userDao = new UserDaoImpl();
    private final CategoryDaoImpl categoryDao = new CategoryDaoImpl();
    private final PromotionDaoImpl promotionDao = new PromotionDaoImpl();
    private final RoleDaoImpl roleDao = new RoleDaoImpl();
    private final UserInfoDaoImpl userInfoDao = new UserInfoDaoImpl();
    private final ProductDao productDao = new ProductDaoImpl();
    private final OrderDao orderDao = new OrderDaoImpl();
    private final UserOrderDao userOrderDao = new UserOrderDaoImpl();
    private static DaoFactory instance = null;

    public static DaoFactory getInstance() {
        if (instance==null)
            instance = new DaoFactory();
        return instance;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public CategoryDaoImpl getCategoryDao() {
        return categoryDao;
    }

    public PromotionDaoImpl getPromotionDao() {
        return promotionDao;
    }

    public RoleDaoImpl getRoleDao() {
        return roleDao;
    }

    public UserInfoDaoImpl getUserInfoDao() {
        return userInfoDao;
    }

    public ProductDao getProductDao() {
        return productDao;
    }

    public OrderDao getOrderDao() {
        return orderDao;
    }

    public UserOrderDao getUserOrderDao() {
        return userOrderDao;
    }
}
