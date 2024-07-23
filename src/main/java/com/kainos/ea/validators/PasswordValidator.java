package com.kainos.ea.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordValidator {
    private static final String PASSWORD_REGEX = "^(?=.*[0-9])"         // at least one digit
                                                + "(?=.*[a-z])"         // at least one lowercase letter
                                                + "(?=.*[A-Z])"         // at least one uppercase letter
                                                + "(?=.*[@#$%^&+=!£*])"    // at least one special character
                                                + "(?=\\S+$)"           // no whitespace allowed
                                                + ".{8,64}$";           // length between 8 and 20 characters

    private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_REGEX);
    public static boolean isValidPassword(String password) {
        if (password == null) {
            return false;
        }
        Matcher matcher = PASSWORD_PATTERN.matcher(password);
        return matcher.matches();
    }
}
