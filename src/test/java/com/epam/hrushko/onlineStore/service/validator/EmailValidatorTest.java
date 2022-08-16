package com.epam.hrushko.onlineStore.service.validator;

import com.epam.hrushko.onlinestore.service.validate.EmailValidator;
import com.epam.hrushko.onlinestore.service.validate.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTest {
    private Validator validator = new EmailValidator();
    private static final String CORRECT_DISCOUNT = "test.test@test.com";
    private static final String INCORRECT_DISCOUNT = "test.test@com";

    @Test
    public void testEmailValidatorShouldReturnTrue() {
        boolean actual = validator.isValid(CORRECT_DISCOUNT);
        assertTrue(actual);
    }

    @Test
    public void testEmailValidatorShouldReturnFalse() {
        boolean actual = validator.isValid(INCORRECT_DISCOUNT);
        assertFalse(actual);
    }
}
