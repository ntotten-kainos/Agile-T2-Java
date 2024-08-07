package com.kainos.ea.service;

import com.kainos.ea.daos.RoleDao;
import com.kainos.ea.enums.Locations;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.exceptions.JobRoleNotFoundException;
import com.kainos.ea.models.JobRoleResponse;
import com.kainos.ea.models.RoleResponse;
import com.kainos.ea.services.RoleService;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Connection;
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
        when(roleDao.getAllJobRoles()).thenThrow(
                FailedToRetrieveException.class);
        assertThrows(FailedToRetrieveException.class, () -> {
            roleService.getAllJobRoles();
        });
    }

    @Test
    void getRoleById_shouldReturnRole_whenDaoReturnsRole()
            throws SQLException, DatabaseConnectionException,
            FailedToRetrieveException, JobRoleNotFoundException {
        int id = 1;
        JobRoleResponse jobRoleResponse = new JobRoleResponse(
                id,
                "Service Designer",
                "As an experienced Service Designer," +
                        " you will be responsible for delivering " +
                        "end-to-end, " +
                        "efficient, and consistent service experiences. " +
                        "You will be an advocate for needs-based design," +
                        " design research, design thinking " +
                        "and service design," +
                        " and are passionate about guiding " +
                        "others in these principles",
                "Leads team in articulating the end to end" +
                        " service through mapping, proposition design " +
                        "and testing plans" +
                        "Feeds into wider team and programme strategy to " +
                        "underpin service delivery",
                Locations.BELFAST,
                "Band 3",
                "Engineering",
                true,
                new java.sql.Timestamp(System.currentTimeMillis()),
                "https://kainossoftwareltd.sharepoint.com" +
                        "/:w:/r/experience%20design/_layouts/15/" +
                        "Doc.aspx?sourcedoc=%7B852A54E6-DC33-4C68-9615-" +
                        "4B3654D44048%7D&file=B2%20Manager.docx&action=" +
                        "default&mobileredirect=true");
        when(roleDao.getRoleById(id)).thenReturn(jobRoleResponse);
        JobRoleResponse result = roleService.getRoleById(id);
        assertEquals(jobRoleResponse, result);
    }

    @Test
    void getRoleById_shouldThrowSQLException_whenDaoThrowsSQLException()
            throws SQLException, DatabaseConnectionException,
            FailedToRetrieveException {
        int id = 1;

        when(roleDao.getRoleById(id)).thenThrow(SQLException.class);

        assertThrows(SQLException.class, () -> {
            roleService.getRoleById(id);
        });
    }

    @Test
    void getJobRoleById_shouldThrowFailedToRetrieveException_whenDaoThrowsFailedToRetrieveException()
            throws SQLException, DatabaseConnectionException,
            FailedToRetrieveException {
        int id = 1;
        when(roleDao.getRoleById(id)).thenThrow(FailedToRetrieveException.class);
        assertThrows(FailedToRetrieveException.class, () -> {
            roleService.getRoleById(id);
        });
    }

    @Test
    void getRoleById_shouldThrowJobRoleNotFoundException_whenDaoReturnsNull()
            throws SQLException, DatabaseConnectionException,
            FailedToRetrieveException {
        int id = 99;
        when(roleDao.getRoleById(id)).thenReturn(null);
        assertThrows(JobRoleNotFoundException.class, () -> {
            roleService.getRoleById(id);
        });
    }
}