package com.kainos.ea.services;

import com.kainos.ea.daos.RoleDao;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.models.JobRoleResponse;
import com.kainos.ea.models.RoleResponse;
import java.sql.SQLException;
import java.util.List;

public class RoleService {
    RoleDao roleDao;
    public RoleService(final RoleDao roleDao) {
        this.roleDao = roleDao;
    }
    public List<RoleResponse> getAllJobRoles()
            throws SQLException, FailedToRetrieveException {
        return roleDao.getAllJobRoles();
    }
    public JobRoleResponse getRoleById(final int id)
            throws SQLException, DatabaseConnectionException,
            FailedToRetrieveException {
        JobRoleResponse roleById = roleDao.getRoleById(id);
         return roleById;
    }
}
