package com.kainos.ea.validators;

import com.kainos.ea.models.LoginRequest;

import static com.kainos.ea.validators.EmailValidator.isValidEmail;
import static com.kainos.ea.validators.PasswordValidator.isValidPassword;

public final class LoginRequestValidator {
    private LoginRequestValidator() { }

    /**
     * Validates a LoginRequest object.
     * @param loginRequest is the object to be validated.
     * @return true if valid, false if not.
     */
    public static boolean validateLoginRequest(
            final LoginRequest loginRequest
    ) {
        return isValidEmail(loginRequest.getEmail())
                && isValidPassword(loginRequest.getPassword());
    }
}
