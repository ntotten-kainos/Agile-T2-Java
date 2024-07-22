package com.kainos.ea.models;

public class User {
    private final String email;
    private final CharSequence password;

    public User(String email, CharSequence password) {
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
