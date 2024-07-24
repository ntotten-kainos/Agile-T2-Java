package com.kainos.ea.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class EmailValidator {
    private EmailValidator() { }

    /**
     * Regex to validate format of email Strings.
     */
    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@"
                                            + "[a-zA-Z0-9.-]+\\."
                                            + "[a-zA-Z]{2,6}$";

    /**
     * The Pattern object to compile the EMAIL_REGEX.
     */
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    /**
     * Checks if a provided email String is formatted correctly.
     * @param email is the String to be validated.
     * @return true if valid, false if not.
     */
    public static boolean isValidEmail(final String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }
}
