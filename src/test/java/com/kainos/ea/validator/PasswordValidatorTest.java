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
    public void isValidPassword_returnsFalse_forPasswordWithNoSpecialChar() {
        String INVALID_PASSWORD = "Invalid1password";
        assertFalse(isValidPassword(INVALID_PASSWORD));
    }

    @Test
    public void isValidPassword_returnsFalse_forPasswordWithWhitespace() {
        String INVALID_PASSWORD = "Invalid1 p@ssword";
        assertFalse(isValidPassword(INVALID_PASSWORD));
    }

    @Test
    public void isValidPassword_returnsFalse_forPasswordWithNoDigits() {
        String INVALID_PASSWORD = "Invalidpassword$";
        assertFalse(isValidPassword(INVALID_PASSWORD));
    }

    @Test
    public void isValidPassword_returnsFalse_forPasswordWithNoUppercase() {
        String INVALID_PASSWORD = "1nvalidp@ssword";
        assertFalse(isValidPassword(INVALID_PASSWORD));
    }

    @Test
    public void isValidPassword_returnsFalse_forPasswordWithNoLowercase() {
        String INVALID_PASSWORD = "1NVALIDP@SSWORD";
        assertFalse(isValidPassword(INVALID_PASSWORD));
    }

    @Test
    public void isValidPassword_returnsFalse_forPasswordTooLong() {
        String INVALID_PASSWORD = "1nval!dP@ssword" +
                                    "1nval!dP@ssword" +
                                    "1nval!dP@ssword" +
                                    "1nval!dP@ssword" +
                                    "1nval!dP@ssword" +
                                    "1nval!dP@ssword" +
                                    "1nval!dP@ssword" +
                                    "1nval!dP@ssword" +
                                    "1nval!dP@ssword" +
                                    "1nval!dP@ssword" +
                                    "1nval!dP@ssword" +
                                    "1nval!dP@ssword";
        assertFalse(isValidPassword(INVALID_PASSWORD));
    }

    @Test
    public void isValidPassword_returnsFalse_forPasswordTooShort() {
        String INVALID_PASSWORD = "!Aa%";
        assertFalse(isValidPassword(INVALID_PASSWORD));
    }
}
