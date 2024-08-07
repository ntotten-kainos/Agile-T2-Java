package com.kainos.ea.services;

import com.kainos.ea.daos.RoleDao;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.exceptions.JobRoleNotFoundException;
import com.kainos.ea.models.JobRoleResponse;
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
    public JobRoleResponse getRoleById(final int id)
            throws SQLException, DatabaseConnectionException,
            FailedToRetrieveException, JobRoleNotFoundException {
         JobRoleResponse roleById = roleDao.getRoleById(id);
         if (roleById == null) {
          throw new JobRoleNotFoundException("Role not found");
         }
         return roleById;
    }
}
