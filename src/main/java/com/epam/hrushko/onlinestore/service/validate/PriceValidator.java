package com.epam.hrushko.onlinestore.service.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Price validator
 */
public class PriceValidator implements Validator {
    private static final String PRICE = "^\\d+\\.\\d{0,2}$";

    @Override
    public boolean isValid(String str) {
        Pattern pattern = Pattern.compile(PRICE);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
