package com.kainos.ea.models;

import java.util.Map;

public class UserRole {

    public static final String ADMIN = "admin";

    public static final String USER = "user";

    int userRoleId;

    private static final Map<Integer, String> userRolesMap = Map.of(
            1, ADMIN,
            2, USER

    );



    public String getUserRoleName(){
        return userRolesMap.get(getUserRoleId());
    }

    public UserRole(int userRoleId) {
        setUserRoleId(userRoleId);
    }

    public int getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(int userRoleId) {
        this.userRoleId = userRoleId;
    }
}
