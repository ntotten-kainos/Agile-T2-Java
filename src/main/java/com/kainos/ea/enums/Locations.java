package com.kainos.ea.enums;

public enum Locations {
    BELFAST("Belfast"),
    DERRY("Derry"),
    TORONTO("Toronto");

    private String value;

    Locations(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Locations fromString(String text) {
        for (Locations l : Locations.values()) {
            if (l.value.equalsIgnoreCase(text)) {
                return l;
            }
        }
        throw new IllegalArgumentException("No enum constant " + text);
    }
}