package com.kainos.ea.enums;

public enum RoleStatus {
    OPEN("Open"), CLOSED("Closed");

    private String value;

    RoleStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public static RoleStatus fromString(String text) {
        for (RoleStatus status : RoleStatus.values()) {
            if (status.value.equalsIgnoreCase(text)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant " + text);
    }

    public static RoleStatus fromBoolean(boolean isOpen) {
        return isOpen ? OPEN : CLOSED;
    }

    public boolean toBoolean() {
        return this == OPEN;
    }
}
