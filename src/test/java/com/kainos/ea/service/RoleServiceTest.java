package com.kainos.ea.service;

import com.kainos.ea.daos.RoleDao;
import com.kainos.ea.enums.JobRoleColumn;
import com.kainos.ea.enums.Direction;
import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.models.RoleResponse;
import com.kainos.ea.services.RoleService;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RoleServiceTest {
    RoleDao roleDao = mock(RoleDao.class);

    RoleService roleService = new RoleService(roleDao);

    @Test
    void getAllJobRoles_shouldReturnListOfOpenRoles_whenDaoReturnsListOfOpenRoles()
            throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = Arrays.asList();

        when(roleDao.getAllJobRoles(null, null)).thenReturn(roles);

        List<RoleResponse> actualRoles = roleService.getAllJobRoles(null, null);

        assertEquals(roles, actualRoles);
    }

    @Test
    void getAllJobRoles_shouldThrowSQLException_whenDaoThrowsSQLException()
            throws SQLException, FailedToRetrieveException {
        when(roleDao.getAllJobRoles(null, null)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> {
            roleService.getAllJobRoles(null, null);
        });
    }

    @Test
    void getAllJobRoles_shouldThrowFailedToRetrieveException_whenDaoThrowsFailedToRetrieveException()
            throws SQLException, FailedToRetrieveException {
        when(roleDao.getAllJobRoles(null, null)).thenThrow(FailedToRetrieveException.class);

        assertThrows(FailedToRetrieveException.class, () -> {
            roleService.getAllJobRoles(null, null);
        });
    }

    @Test
    void getAllJobRoles_shouldReturnListOfRoles_whenDaoReturnsListOfRoles()
            throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = Arrays.asList();

        when(roleDao.getAllJobRoles(JobRoleColumn.ROLENAME.getColumnName(), Direction.ASC.name())).thenReturn(roles);

        List<RoleResponse> actualRoles = roleService.getAllJobRoles(JobRoleColumn.ROLENAME.getColumnName(), Direction.ASC.name());

        assertEquals(roles, actualRoles);
    }

    @Test
    void getAllJobRoles_shouldThrowSQLException_whenDaoThrowsSQLExceptionWithOrdering()
            throws SQLException, FailedToRetrieveException {
        when(roleDao.getAllJobRoles(JobRoleColumn.ROLENAME.getColumnName(), Direction.ASC.name())).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> {
            roleService.getAllJobRoles(JobRoleColumn.ROLENAME.getColumnName(), Direction.ASC.name());
        });
    }

    @Test
    void getAllJobRoles_shouldThrowFailedToRetrieveException_whenDaoThrowsFailedToRetrieveExceptionWithOrdering()
            throws SQLException, FailedToRetrieveException {
        when(roleDao.getAllJobRoles(JobRoleColumn.ROLENAME.getColumnName(), Direction.ASC.name())).thenThrow(FailedToRetrieveException.class);

        assertThrows(FailedToRetrieveException.class, () -> {
            roleService.getAllJobRoles(JobRoleColumn.ROLENAME.getColumnName(), Direction.ASC.name());
        });
    }

    @Test
    void getAllJobRoles_shouldHandleMultipleJobRoleColumns_withDifferentDirections() throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = Arrays.asList();

        for (JobRoleColumn column : JobRoleColumn.values()) {
            when(roleDao.getAllJobRoles(column.getColumnName(), Direction.ASC.name())).thenReturn(roles);
            List<RoleResponse> actualRolesAsc = roleService.getAllJobRoles(column.getColumnName(), Direction.ASC.name());
            assertEquals(roles, actualRolesAsc);

            when(roleDao.getAllJobRoles(column.getColumnName(), Direction.DESC.name())).thenReturn(roles);
            List<RoleResponse> actualRolesDesc = roleService.getAllJobRoles(column.getColumnName(), Direction.DESC.name());
            assertEquals(roles, actualRolesDesc);
        }
    }
}
