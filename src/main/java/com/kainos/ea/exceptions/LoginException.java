package com.kainos.ea.exceptions;

public class LoginException extends Exception{
    public LoginException(Entity entity) {
        super(entity.getEntity() + ": Invalid Login Credentials!");
    }
}
