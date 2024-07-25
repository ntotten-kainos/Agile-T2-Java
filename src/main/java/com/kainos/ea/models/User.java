package com.kainos.ea.models;

public class User {
    /**
     * User email.
     */
    private final String email;

    /**
     * User password.
     */
    private final String password;

    /**
     * Role ID corresponding to
     * User's role.
     */
    private final int userRoleId;

    /**
     * Constructor for User object.
     * @param emailParam is the User's email.
     * @param passwordParam is the User's password.
     * @param roleId is the User's roleId.
     */
    public User(
            final String emailParam,
            final String passwordParam,
            final int roleId
    ) {
        this.userRoleId = roleId;
        this.email = emailParam;
        this.password = passwordParam;
    }

    /**
     * Get the User's email.
     * @return the email String.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Get the User's role ID.
     * @return the role ID integer.
     */
    public int getUserRoleId() {
        return userRoleId;
    }
}
