package com.epam.hrushko.onlinestore.service.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YearValidator implements Validator {
    private static final String YEAR = "^[0-9]{4}$";

    @Override
    public boolean isValid(String str) {
        Pattern pattern = Pattern.compile(YEAR);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
