package com.epam.hrushko.onlineStore.service.validator;

import com.epam.hrushko.onlinestore.service.validate.Validator;
import com.epam.hrushko.onlinestore.service.validate.YearValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class YearValidatorTest {
    private Validator validator = new YearValidator();
    private static final String CORRECT_DISCOUNT = "2022";
    private static final String INCORRECT_DISCOUNT = "-31";

    @Test
    public void testDiscountValidatorShouldReturnTrue() {
        boolean actual = validator.isValid(CORRECT_DISCOUNT);
        assertTrue(actual);
    }

    @Test
    public void testDiscountValidatorShouldReturnFalse() {
        boolean actual = validator.isValid(INCORRECT_DISCOUNT);
        assertFalse(actual);
    }
}
