package com.kainos.ea.models;

public class LoginResponse {
    private final String jwtToken;
    private final String message;

    public LoginResponse(String jwtToken, String message) {
        this.jwtToken = jwtToken;
        this.message = message;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public String getMessage() {
        return message;
    }
}
