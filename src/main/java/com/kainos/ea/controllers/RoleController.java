package com.kainos.ea.controllers;

import com.kainos.ea.exceptions.FailedToRetrieveException;
import com.kainos.ea.services.RoleService;
import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public Response getAllJobRoles() throws SQLException {
        try {
            return Response.ok().entity(roleService.getAllJobRoles()).build();
        } catch (FailedToRetrieveException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("An error occurred while retrieving job roles.")
                    .build();
        }
    }
}