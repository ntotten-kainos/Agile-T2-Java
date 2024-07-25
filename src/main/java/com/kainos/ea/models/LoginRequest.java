package com.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    /**
     * The email to be passed for authentication.
     */
    private String email;

    /**
     * The password to be compared against
     * the hash in database.
     */
    private String password;

    /**
     * A JSON Object that will be retrieved
     * from the POST request payload.
     * @param emailParam is the email for authentication.
     * @param passwordParam is the password to be compared
     *                 with corresponding hashed
     *                 value.
     */
    @JsonCreator
    public LoginRequest(
            @JsonProperty("email") final String emailParam,
            @JsonProperty("password") final String passwordParam) {
        this.email = emailParam;
        this.password = passwordParam;
    }

    /**
     * Getter for email from the LoginRequest.
     * @return the email String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter for password from the LoginRequest.
     * @return the raw password String.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Setter for LoginRequest email.
     * Primarily used for auto-population of request body
     * in Swagger UI.
     * @param emailParam is the email String.
     */
    public void setEmail(final String emailParam) {
        this.email = emailParam;
    }

    /**
     * Setter for LoginRequest password.
     * Primarily used for auto-population of request body
     * in Swagger UI.
     * @param passwordParam is the password String.
     */
    public void setPassword(final String passwordParam) {
        this.password = passwordParam;
    }
}
