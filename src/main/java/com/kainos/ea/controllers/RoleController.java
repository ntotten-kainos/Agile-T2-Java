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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Api("Job Role API")
@Path("/api/job-roles")
public class RoleController {
    private final RoleService roleService;

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
    public Response getAllJobRoles(final @QueryParam(
            "orderBy") String orderBy, final @QueryParam(
                    "direction") String direction) {
        try {
            if (orderBy != null && direction != null) {
                if (!isValidOrderDirection(direction)) {
                    return Response.status(Response.Status.BAD_REQUEST).entity(
                            "Invalid order direction: " + direction).build();
                }
                List<RoleResponse> roles = roleService.getOrderedJobRoles(
                        orderBy, direction);
                return Response.ok().entity(roles).build();
            } else {
                List<RoleResponse> roles = roleService.getAllJobRoles();
                return Response.ok().entity(roles).build();
            }
        } catch (FailedToRetrieveException | SQLException e) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while retrieving job roles.")
                    .build();
        }
    }

    private boolean isValidOrderDirection(final String direction) {
        return "ASC".equalsIgnoreCase(
                direction) || "DESC".equalsIgnoreCase(direction);
    }
}
