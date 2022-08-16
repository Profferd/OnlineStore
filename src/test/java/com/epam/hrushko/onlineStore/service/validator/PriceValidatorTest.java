package com.epam.hrushko.onlineStore.service.validator;

import com.epam.hrushko.onlinestore.service.validate.PriceValidator;
import com.epam.hrushko.onlinestore.service.validate.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PriceValidatorTest {
    private Validator validator = new PriceValidator();
    private static final String CORRECT_DISCOUNT = "35.32";
    private static final String INCORRECT_DISCOUNT = "-31";

    @Test
    public void testPriceValidatorShouldReturnTrue() {
        boolean actual = validator.isValid(CORRECT_DISCOUNT);
        assertTrue(actual);
    }

    @Test
    public void testPriceValidatorShouldReturnFalse() {
        boolean actual = validator.isValid(INCORRECT_DISCOUNT);
        assertFalse(actual);
    }
}
