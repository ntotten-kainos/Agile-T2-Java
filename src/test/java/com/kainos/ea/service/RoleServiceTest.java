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

        when(roleDao.getAllJobRoles()).thenReturn(roles);

        List<RoleResponse> actualRoles = roleService.getAllJobRoles();

        assertEquals(roles, actualRoles);
    }

    @Test
    void getAllJobRoles_shouldThrowSQLException_whenDaoThrowsSQLException()
            throws SQLException, FailedToRetrieveException {
        when(roleDao.getAllJobRoles()).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> {
            roleService.getAllJobRoles();
        });
    }

    @Test
    void getAllJobRoles_shouldThrowFailedToRetrieveException_whenDaoThrowsFailedToRetrieveException()
            throws SQLException, FailedToRetrieveException {
        when(roleDao.getAllJobRoles()).thenThrow(FailedToRetrieveException.class);

        assertThrows(FailedToRetrieveException.class, () -> {
            roleService.getAllJobRoles();
        });
    }

    @Test
    void getOrderedJobRoles_shouldReturnListOfRoles_whenDaoReturnsListOfRoles()
            throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = Arrays.asList();

        when(roleDao.getOrderedJobRoles("roleName", "ASC")).thenReturn(roles);

        List<RoleResponse> actualRoles = roleService.getOrderedJobRoles("roleName", "ASC");

        assertEquals(roles, actualRoles);
    }

    @Test
    void getOrderedJobRoles_shouldThrowSQLException_whenDaoThrowsSQLException()
            throws SQLException, FailedToRetrieveException {
        when(roleDao.getOrderedJobRoles("roleName", "ASC")).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> {
            roleService.getOrderedJobRoles("roleName", "ASC");
        });
    }

    @Test
    void getOrderedJobRoles_shouldThrowFailedToRetrieveException_whenDaoThrowsFailedToRetrieveException()
            throws SQLException, FailedToRetrieveException {
        when(roleDao.getOrderedJobRoles("roleName", "ASC")).thenThrow(FailedToRetrieveException.class);

        assertThrows(FailedToRetrieveException.class, () -> {
            roleService.getOrderedJobRoles("roleName", "ASC");
        });
    }
}
