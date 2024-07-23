package com.kainos.ea.services;

import com.kainos.ea.daos.RoleDao;
import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.models.Role;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class RoleService {

    DatabaseConnector databaseConnector;
    RoleDao roleDao;

    public RoleService(RoleDao roleDao, DatabaseConnector databaseConnector) {
        this.roleDao = roleDao;
        this.databaseConnector = databaseConnector;
    }

    public List<Role> getAllJobRoles()
            throws SQLException, FailedToRetrieveException {
        return roleDao.getAllJobRoles();
    }
}
