package com.epam.hrushko.onlinestore.tag;

import com.epam.hrushko.onlinestore.entity.Promotion;
import com.epam.hrushko.onlinestore.sort.PromotionSortByAsc;
import com.epam.hrushko.onlinestore.sort.PromotionSortByPercent;

import java.util.Collections;
import java.util.List;

public final class PromotionSort {
    public static List<Promotion> sortByName(List<Promotion> promotions) {
        Collections.sort(promotions, new PromotionSortByAsc());
        return promotions;
    }

    public static List<Promotion> sortByPercent(List<Promotion> promotions) {
        Collections.sort(promotions, new PromotionSortByPercent());
        return promotions;
    }
}
