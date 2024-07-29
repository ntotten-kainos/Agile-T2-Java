package com.kainos.ea.controllers;

import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.models.RoleResponse;
import com.kainos.ea.models.UserRole;
import com.kainos.ea.services.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Job Role API")
@Path("/api/job-roles")
public class RoleController {
    RoleService roleService;

    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({UserRole.ADMIN, UserRole.USER})
    @ApiOperation(
            value = "Returns all Job Roles",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION),
            response = RoleResponse.class,
            responseContainer = "List"
    )

    public Response getAllJobRoles() throws SQLException {
        try {
            return Response.ok().entity(roleService.getAllJobRoles()).build();
        } catch (FailedToRetrieveException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while retrieving job roles.")
                    .build();
        }
    }
}
