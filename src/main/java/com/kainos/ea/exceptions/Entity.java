package com.kainos.ea.exceptions;

public enum Entity {
    /**
     * LOGIN_REQUEST entity.
     */
    LOGIN_REQUEST("Login Request");

    /**
     * Value for a custom Entity.
     */
    private final String entity;

    Entity(final String entityStr) {
        this.entity = entityStr;
    }

    /**
     * Get the String for this Entity.
     * @return the String value.
     */
    public String getEntity() {
        return this.entity;
    }
}
