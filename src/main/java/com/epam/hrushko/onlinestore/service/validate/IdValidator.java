package com.epam.hrushko.onlinestore.service.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Id validator
 */
public class IdValidator implements Validator {
    private static final String ID = "(?<![-.])\\b[0-9]+\\b(?!\\.[0-9])";

    @Override
    public boolean isValid(String str) {
        Pattern pattern = Pattern.compile(ID);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
