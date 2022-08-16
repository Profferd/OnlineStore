package com.epam.hrushko.onlinestore.service.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MonthValidator implements Validator {
    private static final String MONTH = "^([1-9]|1[012])$";

    @Override
    public boolean isValid(String str) {
        Pattern pattern = Pattern.compile(MONTH);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
