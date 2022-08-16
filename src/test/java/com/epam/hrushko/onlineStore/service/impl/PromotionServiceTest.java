package com.epam.hrushko.onlineStore.service.impl;

import com.epam.hrushko.onlinestore.connection.ConnectionPool;
import com.epam.hrushko.onlinestore.entity.Promotion;
import com.epam.hrushko.onlinestore.exceptions.ConnectionException;
import com.epam.hrushko.onlinestore.exceptions.ServiceException;
import com.epam.hrushko.onlinestore.service.ProductService;
import com.epam.hrushko.onlinestore.service.PromotionService;
import com.epam.hrushko.onlinestore.service.impl.ProductServiceImpl;
import com.epam.hrushko.onlinestore.service.impl.PromotionServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public class PromotionServiceTest {
    PromotionService productService = new PromotionServiceImpl();

    @BeforeAll
    static void initializeConnectionPool() throws ConnectionException {
        ConnectionPool.getInstance().initialize();
    }

    @Test
    public void testShouldReturnListOfPromotions() throws ServiceException {
        Promotion promotion = new Promotion();
        promotion.setDescription("Testing");
        promotion.setId(1);
        promotion.setName("Test");
        promotion.setDiscount((byte) 25);
        promotion.setPhoto("photo.jpg");
        promotion.setStartDate(new Date(2022, 06, 22));
        promotion.setEndDate(new Date(2022, 06, 24));

        Promotion promotion1 = new Promotion();
        promotion1.setDescription("Testing1");
        promotion1.setId(2);
        promotion1.setName("Test1");
        promotion1.setDiscount((byte) 30);
        promotion1.setPhoto("photo1.jpg");
        promotion1.setStartDate(new Date(2022, 06, 22));
        promotion1.setEndDate(new Date(2022, 06, 24));
        List<Promotion> promotions = List.of(promotion, promotion1);
        List<Promotion> actual = productService.read();
        assertNotEquals(promotions, actual);
    }

    @Test
    public void testShouldReturnPromotionById() throws ServiceException {
        Promotion promotion = new Promotion();
        promotion.setDescription("Testing");
        promotion.setId(1);
        promotion.setName("Test");
        promotion.setDiscount((byte) 25);
        promotion.setPhoto("photo.jpg");
        promotion.setStartDate(new Date(2022, 06, 22));
        promotion.setEndDate(new Date(2022, 06, 24));
        Optional<Promotion> promotion1 = productService.readById(1);
        Optional<Promotion> promotion2 = Optional.of(promotion);
        assertNotEquals(promotion1, promotion2);
    }

}
