package com.epam.hrushko.onlinestore.sort;

import com.epam.hrushko.onlinestore.entity.Promotion;

import java.util.Comparator;

public class PromotionSortByAsc implements Comparator<Promotion> {

    @Override
    public int compare(Promotion o1, Promotion o2) {
        return o1.getName().compareTo(o2.getName());
    }
}
