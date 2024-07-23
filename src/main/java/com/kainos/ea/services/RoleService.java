package com.kainos.ea.services;

import com.kainos.ea.daos.RoleDao;
import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.models.Role;

import java.sql.SQLException;
import java.util.List;

public class RoleService {

    RoleDao roleDao;

    public RoleService(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    public List<Role> getAllJobRoles() throws SQLException, FailedToRetrieveException {
        return roleDao.getAllJobRoles();
    }
}
