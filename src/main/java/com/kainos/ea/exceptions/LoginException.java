package com.kainos.ea.exceptions;

public class LoginException extends Exception {
    /**
     * Custom LoginException for invalid credentials.
     * @param entity is the entity for which login failed.
     */
    public LoginException(final Entity entity) {
        super(entity.getEntity() + ": Invalid Login Credentials!");
    }
}
