package com.kainos.ea.enums;

public enum Direction {
    ASC("ASC"),
    DESC("DESC");

    private final String directionName;

    Direction(final String directionName) {
        this.directionName = directionName;
    }

    public String getDirectionName() {
        return directionName;
    }
}
