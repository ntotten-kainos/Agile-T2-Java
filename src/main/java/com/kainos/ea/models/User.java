package com.kainos.ea.models;

public class User {
    private final String email;
    private final String password;
    private final int userRoleId;

    public User(String email, String password, int userRoleId) {
        this.userRoleId = userRoleId;
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public int getUserRoleId() {
        return userRoleId;
    }
}
