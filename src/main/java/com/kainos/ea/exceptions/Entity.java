package com.kainos.ea.exceptions;

public enum Entity {
    USER("User"),
    LOGIN_REQUEST("Login Request");

    private final String entity;

    Entity (String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return this.entity;
    }
}
