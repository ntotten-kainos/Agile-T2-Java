package com.kainos.ea.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class PasswordValidator {
    private PasswordValidator() { }

    /**
     * Regular expression to enforce password rules.
     */
    private static final String PASSWORD_REGEX
            = "^(?=.*[0-9])"         // At least one digit.
            + "(?=.*[a-z])"         // At least one lowercase letter.
            + "(?=.*[A-Z])"         // At least one uppercase letter
            + "(?=.*[@#$%^&+=!£*])" // At least one special character
            + "(?=\\S+$)"           // No whitespace
            + ".{8,64}$";           // Length between 8 and 64 characters

    /**
     * Pattern that uses PASSWORD_REGEX to validate passwords.
     */
    private static final Pattern PASSWORD_PATTERN
            = Pattern.compile(PASSWORD_REGEX);

    /**
     * Validate the provided raw password.
     * @param password is the password String
     *                 to be validated.
     * @return true if valid, false if not.
     */
    public static boolean isValidPassword(final String password) {
        if (password == null) {
            return false;
        }
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }
}
