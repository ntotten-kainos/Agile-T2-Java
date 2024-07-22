package com.kainos.ea.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest {
    private final String email;
    private final CharSequence password;

    @JsonCreator
    public LoginRequest(
            @JsonProperty String email,
            @JsonProperty CharSequence password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public CharSequence getPassword() {
        return password;
    }
}
