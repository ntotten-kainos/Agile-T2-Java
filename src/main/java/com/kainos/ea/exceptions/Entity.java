package com.kainos.ea.exceptions;

public enum Entity {
    JobRole("JobRole");

    private final String entity;

    Entity(final String entity) {
        this.entity = entity;
    }

    public String getEntity() {
        return this.entity;
    }
}
