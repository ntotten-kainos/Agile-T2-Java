package com.kainos.ea.validator;

import org.junit.jupiter.api.Test;

import static com.kainos.ea.validators.PasswordValidator.isValidPassword;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordValidatorTest {
    @Test
    public void isValidPassword_returnsTrue_forValidPassword() {
        String VALID_PASSWORD = "valid!Pa$$word123";
        assertTrue(isValidPassword(VALID_PASSWORD));
    }

    @Test
    public void isValidPassword_returnsFalse_forInvalidPassword() {
        String INVALID_PASSWORD = "Invalid1";
        assertFalse(isValidPassword(INVALID_PASSWORD));
    }
}
