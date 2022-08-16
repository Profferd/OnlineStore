package com.epam.hrushko.onlinestore.service.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Discount validator
 */
public class DiscoutValidate implements Validator {
    private static final String DISCOUNT = "^[0-9][0-9]?$|^100$";

    @Override
    public boolean isValid(String str) {
        Pattern pattern = Pattern.compile(DISCOUNT);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
