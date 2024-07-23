package com.kainos.ea.validator;

import com.kainos.ea.models.LoginRequest;
import org.junit.jupiter.api.Test;

import static com.kainos.ea.validators.LoginRequestValidator.validateLoginRequest;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginRequestValidatorTest {
    @Test
    public void validateLoginRequest_returnsTrue_forValidEmailAndPassword() {
        LoginRequest VALID_LOGIN_REQUEST = new LoginRequest("valid.email@gmail.com", "valid!Password123");
        assertTrue(validateLoginRequest(VALID_LOGIN_REQUEST));
    }

    @Test
    public void validateLoginRequest_returnsFalse_withInvalidEmail() {
        LoginRequest INVALID_LOGIN_REQUEST = new LoginRequest("invalid.email.gmail.com", "valid!Password123");
        assertFalse(validateLoginRequest(INVALID_LOGIN_REQUEST));
    }

    @Test
    public void validateLoginRequest_returnsFalse_withInvalidPassword() {
        LoginRequest INVALID_LOGIN_REQUEST = new LoginRequest("valid.email@gmail.com", "invalid");
        assertFalse(validateLoginRequest(INVALID_LOGIN_REQUEST));
    }
}
