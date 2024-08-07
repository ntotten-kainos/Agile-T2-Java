package com.kainos.ea.controller;

import com.kainos.ea.controllers.RoleController;
import com.kainos.ea.enums.Direction;
import com.kainos.ea.enums.JobRoleColumn;
import com.kainos.ea.enums.Locations;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.exceptions.JobRoleNotFoundException;
import com.kainos.ea.models.JobRoleResponse;
import com.kainos.ea.models.RoleResponse;
import com.kainos.ea.services.RoleService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class RoleControllerTest {
    RoleService roleService = Mockito.mock(RoleService.class);
    private final RoleController roleController = new RoleController(roleService);
    Timestamp closingDate = Timestamp.valueOf("2024-12-31 23:59:59");

    private final RoleResponse role1 = new RoleResponse(1, "Software Engineer", Locations.BELFAST, "Band A", "Capability A", true, closingDate);
    private final RoleResponse role2 = new RoleResponse(2, "Data Analyst", Locations.DERRY, "Band B", "Capability B", true, closingDate);

    @Test
    void getAllJobRoles_shouldReturnOpenJobRoles() throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = Arrays.asList(role1);

        when(roleService.getAllJobRoles(null, null)).thenReturn(roles);

        Response response = roleController.getAllJobRoles(null, null);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertFalse(((List<RoleResponse>) response.getEntity()).isEmpty());
        assertEquals(roles, response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldReturnEmptyListWhenNoRolesAvailable() throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = Collections.emptyList();

        when(roleService.getAllJobRoles(null, null)).thenReturn(roles);

        Response response = roleController.getAllJobRoles(null, null);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(((List<RoleResponse>) response.getEntity()).isEmpty());
        assertEquals(roles, response.getEntity());
    }
    private final RoleResponse roleResponse = new RoleResponse(1, "Software Engineer", Locations.BELFAST, "Band", "Capability", true, closingDate);
    private final JobRoleResponse jobRoleResponse = new JobRoleResponse(1, "Software Engineer", "Description", "Responsibilities", Locations.BELFAST, "Band", "Capability", true, closingDate, "Specification");

    @Test
    void getAllJobRoles_withOrdering_shouldReturnOrderedJobRoles() throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = Arrays.asList(role1, role2);
        when(roleService.getAllJobRoles(JobRoleColumn.ROLENAME.getColumnName(), Direction.ASC.getDirectionName())).thenReturn(roles);
        Response response = roleController.getAllJobRoles(JobRoleColumn.ROLENAME.getColumnName(), Direction.ASC.getDirectionName());
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertFalse(((List<RoleResponse>) response.getEntity()).isEmpty());
        assertEquals(roles, response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldReturnInternalServerError_whenFailedToRetrieveExceptionThrown() throws SQLException, FailedToRetrieveException {
        when(roleService.getAllJobRoles(null, null)).thenThrow(FailedToRetrieveException.class);
        Response response = roleController.getAllJobRoles(null, null);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job roles.", response.getEntity());
    }

    @Test
    void getAllJobRoles_withOrdering_shouldReturnInternalServerError_whenFailedToRetrieveExceptionThrown() throws SQLException, FailedToRetrieveException {
        when(roleService.getAllJobRoles(JobRoleColumn.ROLENAME.getColumnName(), Direction.ASC.getDirectionName())).thenThrow(FailedToRetrieveException.class);
        Response response = roleController.getAllJobRoles(JobRoleColumn.ROLENAME.getColumnName(), Direction.ASC.getDirectionName());
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job roles.", response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldReturnInternalServerError_whenSQLExceptionThrown() throws SQLException, FailedToRetrieveException {
        when(roleService.getAllJobRoles(null, null)).thenThrow(SQLException.class);
        Response response = roleController.getAllJobRoles(null, null);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job roles.", response.getEntity());
    }

    @Test
    void getAllJobRoles_withOrdering_shouldReturnInternalServerError_whenSQLExceptionThrown() throws SQLException, FailedToRetrieveException {
        when(roleService.getAllJobRoles(JobRoleColumn.ROLENAME.getColumnName(), Direction.ASC.getDirectionName())).thenThrow(SQLException.class);
        Response response = roleController.getAllJobRoles(JobRoleColumn.ROLENAME.getColumnName(), Direction.ASC.getDirectionName());
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job roles.", response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldHandleInvalidColumnName() throws SQLException, FailedToRetrieveException {
        when(roleService.getAllJobRoles("invalidColumn", Direction.ASC.getDirectionName())).thenThrow(FailedToRetrieveException.class);
        Response response = roleController.getAllJobRoles("invalidColumn", Direction.ASC.getDirectionName());
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job roles.", response.getEntity());
    }

    @Test
    void getRoleById_shouldReturnJobRole_whenIdIsValid() throws SQLException, FailedToRetrieveException, DatabaseConnectionException,
            JobRoleNotFoundException {
        when(roleService.getRoleById(1)).thenReturn(jobRoleResponse);
        Response response = roleController.getRoleById(1);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(jobRoleResponse, response.getEntity());
    }

    @Test
    void getRoleById_shouldReturnInternalServerError_whenFailedToRetrieveExceptionThrown() throws SQLException, DatabaseConnectionException, FailedToRetrieveException, JobRoleNotFoundException {
        when(roleService.getRoleById(1)).thenThrow(FailedToRetrieveException.class);
        Response response = roleController.getRoleById(1);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals("Job role by ID has not been found.", response.getEntity());
    }

    @Test
    void getRoleById_shouldReturnInternalServerError_whenSQLExceptionThrown() throws SQLException, DatabaseConnectionException, FailedToRetrieveException, JobRoleNotFoundException {
        when(roleService.getRoleById(1)).thenThrow(SQLException.class);
        Response response = roleController.getRoleById(1);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job role by ID.", response.getEntity());
    }

    @Test
    void getRoleById_shouldReturnInternalServerError_whenDatabaseConnectionExceptionThrown() throws SQLException, FailedToRetrieveException, DatabaseConnectionException, JobRoleNotFoundException {
        when(roleService.getRoleById(1)).thenThrow(DatabaseConnectionException.class);
        Response response = roleController.getRoleById(1);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job role by ID.", response.getEntity());
    }

    @Test
    void getRoleById_shouldReturnNull_whenRoleNotFound() throws SQLException, DatabaseConnectionException, FailedToRetrieveException, JobRoleNotFoundException{
        when(roleService.getRoleById(1)).thenReturn(null);
        Response response = roleController.getRoleById(1);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNull(response.getEntity());
    }
}