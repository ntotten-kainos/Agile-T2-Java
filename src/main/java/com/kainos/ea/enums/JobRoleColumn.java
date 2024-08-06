package com.kainos.ea.enums;

public enum JobRoleColumn {
    ROLE_NAME("roleName"),
    LOCATION("location"),
    BAND("band"),
    CAPABILITY("capability"),
    CLOSING_DATE("closingDate"),
    STATUS("status");

    private final String columnName;

    JobRoleColumn(final String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
