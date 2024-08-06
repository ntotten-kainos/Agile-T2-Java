package com.kainos.ea.controllers;

import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.exceptions.JobRoleNotFoundException;
import com.kainos.ea.models.UserRole;
import com.kainos.ea.services.RoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;
@Api("Job Role API")
@Path("/api/job-roles")
public class RoleController {
    RoleService roleService;
    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({UserRole.ADMIN_USER, UserRole.APPLICANT_USER})
    @ApiOperation(
            value = "Returns all Job Roles",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION),
            response = List.class)

    public Response getAllJobRoles() throws SQLException {
        try {
            return Response.ok().entity(roleService.getAllJobRoles()).build();
        } catch (FailedToRetrieveException | SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while retrieving job roles.")
                    .build();
        }
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @RolesAllowed({UserRole.ADMIN_USER, UserRole.APPLICANT_USER})
    @ApiOperation(
            value = "Returns a Job Role by ID",
            authorizations = @Authorization(value = HttpHeaders.AUTHORIZATION),
            response = List.class)
    public Response getRoleById(@PathParam("id") final int id) {
        try {
            return Response.ok().entity(roleService.getRoleById(id)).build();
        } catch (FailedToRetrieveException | JobRoleNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("An error occurred while retrieving "
                            + "job role by ID.")
                    .build();
        } catch
        (SQLException | DatabaseConnectionException
                        e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while retrieving "
                            + "job role by ID.")
                    .build();
        }
    }
}
