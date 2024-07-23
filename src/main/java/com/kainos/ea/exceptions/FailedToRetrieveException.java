package com.kainos.ea.exceptions;

public class FailedToRetrieveException extends Exception{
    public FailedToRetrieveException(final Entity entity) {
        super(entity.getEntity() + " Failed to retrieve");
    }
}
