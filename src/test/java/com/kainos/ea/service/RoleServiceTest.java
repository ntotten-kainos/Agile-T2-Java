package com.kainos.ea.service;

import com.kainos.ea.daos.RoleDao;
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
    void getAllJobRoles_shouldReturnsListOfOpenRoles_whenDaoReturnsListOfOpenRoles()
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
    void getAllJobRoles_withOrdering_shouldReturnListOfRoles_whenDaoReturnsListOfRoles()
            throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = Arrays.asList();

        when(roleDao.getAllJobRoles("roleName", "ASC")).thenReturn(roles);

        List<RoleResponse> actualRoles = roleService.getAllJobRoles("roleName", "ASC");

        assertEquals(roles, actualRoles);
    }

    @Test
    void getAllJobRoles_withOrdering_shouldThrowSQLException_whenDaoThrowsSQLException()
            throws SQLException, FailedToRetrieveException {
        when(roleDao.getAllJobRoles("roleName", "ASC")).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> {
            roleService.getAllJobRoles("roleName", "ASC");
        });
    }

    @Test
    void getAllJobRoles_withOrdering_shouldThrowFailedToRetrieveException_whenDaoThrowsFailedToRetrieveException()
            throws SQLException, FailedToRetrieveException {
        when(roleDao.getAllJobRoles("roleName", "ASC")).thenThrow(FailedToRetrieveException.class);

        assertThrows(FailedToRetrieveException.class, () -> {
            roleService.getAllJobRoles("roleName", "ASC");
        });
    }
}
