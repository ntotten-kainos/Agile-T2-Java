package com.kainos.ea.validators;

import com.kainos.ea.models.LoginRequest;

import static com.kainos.ea.validators.EmailValidator.isValidEmail;
import static com.kainos.ea.validators.PasswordValidator.isValidPassword;

public class LoginRequestValidator {
    public static boolean validateLoginRequest(LoginRequest loginRequest) {
        return isValidEmail(loginRequest.getEmail()) && isValidPassword(loginRequest.getPassword());
    }
}
