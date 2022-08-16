package com.epam.hrushko.onlinestore.service.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements Validator {
    private static final String EMAIL = "^[A-z0-9]{1}([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z]+(\\.[a-z]+)*\\.[a-z]{2,6}$";

    @Override
    public boolean isValid(String str) {
        Pattern pattern = Pattern.compile(EMAIL);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
