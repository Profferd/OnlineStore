package com.epam.hrushko.onlinestore.service.impl;

import com.epam.hrushko.onlinestore.dao.DaoFactory;
import com.epam.hrushko.onlinestore.dao.PromotionDao;
import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.entity.Promotion;
import com.epam.hrushko.onlinestore.exceptions.DaoException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.PromotionService;
import com.epam.hrushko.onlinestore.service.validate.DiscoutValidate;
import com.epam.hrushko.onlinestore.service.validate.Validator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Promotion service class
 */
public class PromotionServiceImpl implements PromotionService {
    private static final double HUNDRED_PERCENT = 100;
    private static final Logger LOGGER = LogManager.getLogger();

    @Override
    public List<Promotion> read() throws ServiceException {
        try {
            PromotionDao promotionDao = DaoFactory.getInstance().getPromotionDao();
            List<Promotion> result = null;
            result = promotionDao.read();
            return result;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Optional<Promotion> readById(int promId) throws ServiceException {
        try {
            PromotionDao promotionDao = DaoFactory.getInstance().getPromotionDao();
            Optional<Promotion> result;
            result = promotionDao.readById(promId);
            if (!checkRelevanceOfPromotion(result)) {
                return Optional.empty();
            }
            return result;
        } catch (DaoException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public Double calcNewPrice(double price, int discount) throws ServiceException {
        return price * (HUNDRED_PERCENT - discount) / HUNDRED_PERCENT;
    }

    @Override
    public Map<String, Double> getNewPrices(List<Product> products) throws ServiceException {
        Map<String, Double> newPrices = new HashMap<>();

        for (Product product : products) {
            if (product.getPromotionId() != 0) {
                Optional<Promotion> promotion = readById(product.getPromotionId());
                if (checkRelevanceOfPromotion(promotion)) {
                    Double newPrice = calcNewPrice(product.getPrice(), promotion.get().getDiscount());
                    double scale = Math.pow(10, 2);
                    newPrice = Math.ceil(newPrice * scale) / scale;
                    newPrices.put(product.getName(), newPrice);
                }
            }
        }
        return newPrices;
    }

    @Override
    public boolean creat(String promotionName, String photo, String startDateString, String endDateString, String description, String discountString) throws ServiceException {
        if (promotionName == null || photo == null || startDateString == null || endDateString == null ||
                description == null || discountString == null) {
            return false;
        }

        try {
            PromotionDao promotionDao = DaoFactory.getInstance().getPromotionDao();
            Optional<Promotion> existPromotion = promotionDao.readByName(promotionName);
            if (existPromotion.isPresent()) {
                return false;
            }

            Date startDate = new SimpleDateFormat("yyyy-MM-dd").parse(startDateString);
            Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(endDateString);

            if (!isValid(startDate, endDate)) {
                return false;
            }

            Validator discountValidator = new DiscoutValidate();
            if (!discountValidator.isValid(discountString)) {
                return false;
            }

            byte discount = Byte.parseByte(discountString);

            Promotion promotion = new Promotion();
            promotion.setName(promotionName);
            promotion.setPhoto(photo);
            promotion.setDescription(description);
            promotion.setStartDate(startDate);
            promotion.setEndDate(endDate);
            promotion.setDiscount(discount);
            promotionDao.create(promotion);
            return true;
        } catch (DaoException | ParseException e) {
            LOGGER.log(Level.ERROR, e.getMessage());
            throw new ServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean checkRelevanceOfPromotion(Optional<Promotion> promotion) {
        if (promotion.isPresent()) {
            Date currentDate = new Date();
            return promotion.get().getEndDate().compareTo(currentDate) >= 0;
        }
        return false;
    }

    private boolean isValid(Date startDate, Date endDate) {
        Date currentDate = new Date();
        if (!(startDate.compareTo(currentDate) >= 0)) {
            return false;
        }
        if (!(endDate.compareTo(startDate) >= 0)) {
            return false;
        }
        return true;
    }
}
