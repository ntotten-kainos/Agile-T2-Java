package com.kainos.ea.enums;

public enum Bands {
    BAND1("BAND1"), BAND2("BAND2"), BAND3("BAND3");

    private String value;

    Bands(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static Bands fromString(final String text) {
        for (Bands b : Bands.values()) {
            if (b.value.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new IllegalArgumentException("No enum constant " + text);
    }
}
