package com.kainos.ea.exceptions;

public class DatabaseConnectionException extends Exception {
    public DatabaseConnectionException(final Exception e) {
        super(e);
    }
}
