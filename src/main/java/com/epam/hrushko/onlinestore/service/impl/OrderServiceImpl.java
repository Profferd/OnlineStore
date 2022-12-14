package com.epam.hrushko.onlinestore.service.impl;

import com.epam.hrushko.onlinestore.dao.DaoFactory;
import com.epam.hrushko.onlinestore.dao.OrderDao;
import com.epam.hrushko.onlinestore.dao.ProductDao;
import com.epam.hrushko.onlinestore.dao.PromotionDao;
import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.entity.Promotion;
import com.epam.hrushko.onlinestore.entity.UserOrder;
import com.epam.hrushko.onlinestore.exceptions.DaoException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.OrderService;
import com.epam.hrushko.onlinestore.service.PromotionService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * Order service class
 */
public class OrderServiceImpl implements OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderServiceImpl.class);

    /**
     * Find user by user id
     * @param userId
     * @return order
     * @throws ServiceException
     */
    @Override
    public List<Order> readByUser(int userId) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            List<Order> result = null;
            result = orderDao.readByUser(userId);
            return result;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Find orders by user order id
     * @param userOrderId
     * @return all orders
     * @throws ServiceException
     */
    @Override
    public List<Order> readByUserOrder(int userOrderId) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            List<Order> result = null;
            result = orderDao.readByUserOrder(userOrderId);
            return result;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Delete order by order id
     * @param orderId
     * @return true or false
     * @throws ServiceException
     */
    @Override
    public boolean delete(int orderId) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            Optional<Order> order = orderDao.readById(orderId);
            if (!order.isPresent()) {
                return false;
            }
            orderDao.delete(orderId);
            return true;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Create order
     * @param userId
     * @param productId
     * @param number
     * @return create or not
     * @throws ServiceException
     */
    @Override
    public boolean create(int userId, int productId, int number) throws ServiceException {
        try {
            OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            List<Order> existOrders = orderDao.readUserAndProduct(userId, productId);
            if (!(existOrders.size() == 0)) {
                return false;
            }
            Order order = new Order();
            order.setUserId(userId);
            order.setProductId(productId);
            order.setNumber(number);
            orderDao.create(order);
            return true;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Calculating total cost of products
     * @param orders
     * @return total cost
     * @throws ServiceException
     */
    @Override
    public double calculateTotalCost(List<Order> orders) throws ServiceException {
        double totalCost = 0;
        try {
            ProductDao productDao = DaoFactory.getInstance().getProductDao();
            for (Order order : orders) {
                int productId = order.getProductId();

                Optional<Product> product = productDao.readById(productId);
                if (product.isPresent()) {
                    if (product.get().getPromotionId() != 0) {
                        PromotionDao promotionDao = DaoFactory.getInstance().getPromotionDao();
                        Optional<Promotion> promotion = promotionDao.readById(product.get().getPromotionId());
                        PromotionService promotionService = new PromotionServiceImpl();
                        if (promotionService.checkRelevanceOfPromotion(promotion)) {
                            double newPrice = promotionService.calcNewPrice(product.get().getPrice(), promotion.get().getDiscount());
                            double scale = Math.pow(10, 2);
                            newPrice = Math.ceil(newPrice * scale) / scale;
                            product.get().setPrice(newPrice);
                        }
                    }
                    totalCost += product.get().getPrice() * order.getNumber();
                }
            }
            return totalCost;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    /**
     * Find all orders using user orders
     * @param userOrders
     * @return all orders
     * @throws ServiceException
     */
    @Override
    public List<Order> readOrders(List<UserOrder> userOrders) throws ServiceException {
        List<Order> orders = new LinkedList<>();

        for (UserOrder userOrder : userOrders) {
            List<Order> order = readByUserOrder(userOrder.getId());
            if (!order.isEmpty()) {
                orders.addAll(order);
            }
        }
        return orders;
    }
}
