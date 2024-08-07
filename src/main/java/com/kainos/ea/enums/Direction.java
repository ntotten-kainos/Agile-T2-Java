package com.kainos.ea.enums;

public enum Direction {
    ASC,
    DESC;

    public static boolean isValid(final String value) {
        for (Direction direction : Direction.values()) {
            if (direction.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}
