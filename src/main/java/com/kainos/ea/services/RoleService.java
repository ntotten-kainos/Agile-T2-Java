package com.kainos.ea.services;

import com.kainos.ea.daos.RoleDao;
import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.models.RoleResponse;

import java.sql.SQLException;
import java.util.List;

public class RoleService {

    private final RoleDao roleDao;

    public RoleService(final RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<RoleResponse> getAllJobRoles(
            final String orderBy, final String direction)
            throws SQLException, FailedToRetrieveException {
        return roleDao.getAllJobRoles(orderBy, direction);
    }
}

