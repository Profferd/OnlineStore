package com.epam.hrushko.onlinestore.service.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NameValidator implements Validator {
    private static final String NAME = "^[A-ZА-Я]{1}[a-zа-я]{1,20}$";

    @Override
    public boolean isValid(String str) {
        Pattern pattern = Pattern.compile(NAME);
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
