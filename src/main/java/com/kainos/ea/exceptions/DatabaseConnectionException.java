package com.kainos.ea.exceptions;

public class DatabaseConnectionException extends Exception {
    /**
     * Constructor for DatabaseConnectionException.
     * @param e is a generic exception, used to call
     *          the super constructor for the
     *          Exception class.
     */
    public DatabaseConnectionException(final Exception e) {
        super(e);
    }
}
