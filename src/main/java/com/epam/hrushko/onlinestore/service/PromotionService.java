package com.epam.hrushko.onlinestore.service;

import com.epam.hrushko.onlinestore.entity.Product;
import com.epam.hrushko.onlinestore.entity.Promotion;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PromotionService {

    List<Promotion> read() throws ServiceException;

    Optional<Promotion> readById(int promId) throws ServiceException;

    Double calcNewPrice(double price, int discount) throws ServiceException;

    Map<String, Double> getNewPrices(List<Product> products) throws ServiceException;

    boolean creat(String promotionName, String photo, String startDateString, String endDateString,
                  String description, String discountString) throws ServiceException;

    boolean checkRelevanceOfPromotion(Optional<Promotion> promotion);

}
