package com.kainos.ea.exceptions;

public class DatabaseConnectionException extends Exception{
    public DatabaseConnectionException(Exception e) {
        super(e);
    }
}
