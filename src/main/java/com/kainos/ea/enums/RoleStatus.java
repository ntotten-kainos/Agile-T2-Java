package com.kainos.ea.enums;

public enum RoleStatus {
    OPEN("Open"),
    CLOSED("Closed");

    private String value;

    RoleStatus(final String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static RoleStatus fromString(final String text) {
        for (RoleStatus status : RoleStatus.values()) {
            if (status.value.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant " + text);
    }

    public static RoleStatus fromBoolean(final boolean isOpen) {
        return isOpen ? OPEN : CLOSED;
    }

    public boolean toBoolean() {
        return this == OPEN;
    }
}
