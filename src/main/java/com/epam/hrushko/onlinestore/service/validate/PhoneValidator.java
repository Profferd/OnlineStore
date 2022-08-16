package com.epam.hrushko.onlinestore.service.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Phone validator
 */
public class PhoneValidator implements Validator {
    private static final String PHONE = "^[0-9]{10,13}$";

    @Override
    public boolean isValid(String str) {
        Pattern pattern = Pattern.compile(PHONE);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
