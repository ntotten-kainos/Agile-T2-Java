package com.kainos.ea.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])"         // At least one digit
                                                + "(?=.*[a-z])"         // At least one lowercase letter
                                                + "(?=.*[A-Z])"         // At least one uppercase letter
                                                + "(?=.*[@#$%^&+=!£*])" // At least one special character
                                                + "(?=\\S+$)"           // No whitespace
                                                + ".{8,64}$";           // Length between 8 and 64 characters

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }
}
