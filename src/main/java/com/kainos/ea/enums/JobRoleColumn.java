package com.kainos.ea.enums;

public enum JobRoleColumn {
    ROLENAME("roleName"),
    LOCATION("location"),
    BAND("band"),
    CAPABILITY("capability"),
    CLOSINGDATE("closingDate"),
    STATUS("status");

    private final String columnName;

    JobRoleColumn(final String columnName) {
        this.columnName = columnName;
    }

    public String getColumnName() {
        return columnName;
    }
}
