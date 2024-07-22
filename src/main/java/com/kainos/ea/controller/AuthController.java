package com.kainos.ea.controller;

import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.InvalidException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.services.AuthService;
import io.swagger.annotations.Api;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

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
