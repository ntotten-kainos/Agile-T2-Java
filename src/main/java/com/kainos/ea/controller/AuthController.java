package com.kainos.ea.controller;

import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.InvalidException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.services.AuthService;
import io.swagger.annotations.Api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Api("Auth API")
@Path("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        try {
            return Response.ok().entity(authService.login(loginRequest)).build();
        } catch (SQLException | DatabaseConnectionException e) {
            return Response.serverError().build();
        } catch (InvalidException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
