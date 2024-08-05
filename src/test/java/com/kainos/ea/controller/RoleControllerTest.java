package com.kainos.ea.controller;

import com.kainos.ea.controllers.RoleController;
import com.kainos.ea.enums.Locations;
import com.kainos.ea.exceptions.FailedToRetrieveException;
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

        when(roleService.getAllJobRoles()).thenReturn(roles);

        Response response = roleController.getAllJobRoles(null, null);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertFalse(((List<RoleResponse>) response.getEntity()).isEmpty());
        assertEquals(roles, response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldReturnEmptyListWhenNoRolesAvailable() throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = Collections.emptyList();

        when(roleService.getAllJobRoles()).thenReturn(roles);

        Response response = roleController.getAllJobRoles(null, null);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertTrue(((List<RoleResponse>) response.getEntity()).isEmpty());
        assertEquals(roles, response.getEntity());
    }

    @Test
    void getOrderedJobRoles_shouldReturnOrderedJobRoles() throws SQLException, FailedToRetrieveException {
        List<RoleResponse> roles = Arrays.asList(role1, role2);

        when(roleService.getOrderedJobRoles("roleName", "ASC")).thenReturn(roles);

        Response response = roleController.getAllJobRoles("roleName", "ASC");

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertFalse(((List<RoleResponse>) response.getEntity()).isEmpty());
        assertEquals(roles, response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldReturnInternalServerError_whenFailedToRetrieveExceptionThrown() throws SQLException, FailedToRetrieveException {
        when(roleService.getAllJobRoles()).thenThrow(FailedToRetrieveException.class);

        Response response = roleController.getAllJobRoles(null, null);

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job roles.", response.getEntity());
    }

    @Test
    void getOrderedJobRoles_shouldReturnInternalServerError_whenFailedToRetrieveExceptionThrown() throws SQLException, FailedToRetrieveException {
        when(roleService.getOrderedJobRoles("roleName", "ASC")).thenThrow(FailedToRetrieveException.class);

        Response response = roleController.getAllJobRoles("roleName", "ASC");

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job roles.", response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldReturnInternalServerError_whenSQLExceptionThrown() throws SQLException, FailedToRetrieveException {
        when(roleService.getAllJobRoles()).thenThrow(SQLException.class);

        Response response = roleController.getAllJobRoles(null, null);

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job roles.", response.getEntity());
    }

    @Test
    void getOrderedJobRoles_shouldReturnInternalServerError_whenSQLExceptionThrown() throws SQLException, FailedToRetrieveException {
        when(roleService.getOrderedJobRoles("roleName", "ASC")).thenThrow(SQLException.class);

        Response response = roleController.getAllJobRoles("roleName", "ASC");

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job roles.", response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldHandleInvalidOrderDirection() throws SQLException, FailedToRetrieveException {
        Response response = roleController.getAllJobRoles("roleName", "INVALID");

        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("Invalid order direction: INVALID", response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldHandleInvalidColumnName() throws SQLException, FailedToRetrieveException {
        when(roleService.getOrderedJobRoles("invalidColumn", "ASC")).thenThrow(FailedToRetrieveException.class);

        Response response = roleController.getAllJobRoles("invalidColumn", "ASC");

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
        assertEquals("An error occurred while retrieving job roles.", response.getEntity());
    }
}

