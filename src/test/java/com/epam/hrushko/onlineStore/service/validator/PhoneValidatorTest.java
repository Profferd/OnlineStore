package com.epam.hrushko.onlineStore.service.validator;

import com.epam.hrushko.onlinestore.service.validate.PhoneValidator;
import com.epam.hrushko.onlinestore.service.validate.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneValidatorTest {
    private Validator validator = new PhoneValidator();
    private static final String CORRECT_DISCOUNT = "3512412322";
    private static final String INCORRECT_DISCOUNT = "31";

    @Test
    public void testPhoneValidatorShouldReturnTrue() {
        boolean actual = validator.isValid(CORRECT_DISCOUNT);
        assertTrue(actual);
    }

    @Test
    public void testPhoneValidatorShouldReturnFalse() {
        boolean actual = validator.isValid(INCORRECT_DISCOUNT);
        assertFalse(actual);
    }
}
