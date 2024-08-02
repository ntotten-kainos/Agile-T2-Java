package com.kainos.ea.models;

import java.util.Map;

public class UserRole {

    public static final String ADMIN_USER = "admin";

    public static final String APPLICANT_USER = "applicant";

    int userRoleId;

    private static final Map<Integer, String> USERMAP = Map.of(
            1, ADMIN_USER,
            3, APPLICANT_USER

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
        return USERMAP.get(getUserRoleId());
    }
}
