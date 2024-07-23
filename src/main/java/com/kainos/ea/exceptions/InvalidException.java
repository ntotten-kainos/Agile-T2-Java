package com.kainos.ea.exceptions;

public class InvalidException extends Throwable {
    public InvalidException(Entity entity) {
        super(entity.getEntity() + ": Invalid Request!");
    }
}
