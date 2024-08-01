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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class RoleControllerTest {
    RoleService roleService = Mockito.mock(RoleService.class);

    private final RoleController roleController =
            new RoleController(roleService);

    Timestamp closingDate = Timestamp.valueOf("2024-12-31 23:59:59");

    private final RoleResponse role =
            new RoleResponse(1, "Software Engineer", Locations.BELFAST,
                    "", "",
                    true, closingDate);

    @Test
    void getAllJobRoles_shouldReturnOpenJobRoles() throws SQLException,
            FailedToRetrieveException {
        List<RoleResponse> roles = Arrays.asList(role);

        when(roleService.getAllJobRoles()).thenReturn(roles);

        Response response = roleController.getAllJobRoles();

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertFalse(((List<RoleResponse>) response.getEntity()).isEmpty());
        assertEquals(roles, response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldReturnInternalServerError_whenFailedToRetrieveExceptionThrown()
            throws SQLException, FailedToRetrieveException {
        when(roleService.getAllJobRoles()).thenThrow(
                FailedToRetrieveException.class);

        Response response = roleController.getAllJobRoles();

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                response.getStatus());
        assertEquals("An error occurred while retrieving job roles.",
                response.getEntity());
    }

    @Test
    void getAllJobRoles_shouldReturnInternalServerError_whenSQLExceptionThrown()
            throws SQLException, FailedToRetrieveException {
        when(roleService.getAllJobRoles()).thenThrow(SQLException.class);

        Response response = roleController.getAllJobRoles();

        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                response.getStatus());
        assertEquals("An error occurred while retrieving job roles.",
                response.getEntity());
    }
}
