package com.kainos.ea.models;

import java.util.Map;

public class UserRole {

    public static final String ADMIN = "admin";

    public static final String USER = "user";

    int userRoleId;

    private static final Map<Integer, String> ROLESMAP = Map.of(
            1, ADMIN,
            2, USER

    );
    public UserRole(final int userRoleId) {
        setUserRoleId(userRoleId);
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(final int userRoleId) {
        this.userRoleId = userRoleId;
    }
    public String getRoleName() {
        return ROLESMAP.get(getUserRoleId());
    }
}
