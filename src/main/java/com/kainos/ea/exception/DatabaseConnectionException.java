package com.kainos.ea.exception;

public class DatabaseConnectionException extends Throwable {
    public DatabaseConnectionException(Exception e) {
        super(e);
    }
}
