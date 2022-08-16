package com.epam.hrushko.onlinestore.service.impl;

import com.epam.hrushko.onlinestore.dao.DaoFactory;
import com.epam.hrushko.onlinestore.dao.OrderDao;
import com.epam.hrushko.onlinestore.dao.UserOrderDao;
import com.epam.hrushko.onlinestore.entity.Order;
import com.epam.hrushko.onlinestore.entity.UserOrder;
import com.epam.hrushko.onlinestore.exceptions.DaoException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.UserInfoService;
import com.epam.hrushko.onlinestore.service.UserOrderService;
import com.epam.hrushko.onlinestore.service.validate.MonthValidator;
import com.epam.hrushko.onlinestore.service.validate.Validator;
import com.epam.hrushko.onlinestore.service.validate.YearValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * User order service class
 */
public class UserOrderServiceImpl implements UserOrderService {
    private static final Logger LOGGER = LogManager.getLogger();
    @Override
    public Optional<UserOrder> readById(int userOrderId) throws ServiceException {
        try {
            UserOrderDao userOrderDao = DaoFactory.getInstance().getUserOrderDao();
            Optional<UserOrder> result;
            result = userOrderDao.findById(userOrderId);
            System.out.println(result);
            return result;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<UserOrder> readByStatus(String status) throws ServiceException {
        try {
            UserOrderDao userOrderDao = DaoFactory.getInstance().getUserOrderDao();
            List<UserOrder> result = null;
            result = userOrderDao.findByStatus(status);
            return result;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean updateStatusById(int userOrderId, String status) throws ServiceException {
        try {
            UserOrderDao userOrderDao = DaoFactory.getInstance().getUserOrderDao();
            Optional<UserOrder> userOrder = userOrderDao.findById(userOrderId);
            if (!userOrder.isPresent()) {
                return false;
            }
            userOrderDao.updateStatus(userOrderId, status);
            return true;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean create(List<Order> orders, String address, String deliveryDateString, double totalPrice) throws ServiceException {
        try {
            if (address == null || deliveryDateString == null || orders.size() < 1) {
                return false;
            }

            Date deliveryDate = new SimpleDateFormat("yyyy-MM-dd").parse(deliveryDateString);
            Date currentDate = new Date();
            if (!isDateValid(deliveryDate, currentDate)) {
                return false;
            }
            UserOrder userOrder = new UserOrder();
            userOrder.setAddress(address);
            userOrder.setDeliveryDate(deliveryDate);
            userOrder.setOrderDate(currentDate);
            userOrder.setStatus("pending");
            UserOrderDao userOrderDao = DaoFactory.getInstance().getUserOrderDao();
            int userOrderId = userOrderDao.create(userOrder);
            OrderDao orderDao = DaoFactory.getInstance().getOrderDao();
            for (Order order : orders) {
                orderDao.update(order.getId(), userOrderId);
            }
            return true;
        } catch (ParseException | DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<UserOrder> getUserOrdersFromOrders(List<Order> orders) throws ServiceException {
        List<UserOrder> userOrders = new LinkedList<>();

        for (Order order : orders) {
            Optional<UserOrder> userOrder = readById(order.getOrderId());
            if (userOrder.isPresent()) {
                boolean checkIfOrderExistFlag = false;
                for (UserOrder value : userOrders) {
                    if (value.getId() == userOrder.get().getId()) {
                        checkIfOrderExistFlag = true;
                    }
                }
                if (!checkIfOrderExistFlag) {
                    userOrders.add(userOrder.get());
                }
            }
        }

        return userOrders;
    }

    private boolean isDateValid(Date deliveryDate, Date currentDate) {
        return deliveryDate.compareTo(currentDate) > 0;
    }

}
