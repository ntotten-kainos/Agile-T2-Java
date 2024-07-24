package com.kainos.ea.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.LoginException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.models.LoginResponse;
import com.kainos.ea.services.AuthService;
import io.swagger.annotations.Api;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static com.kainos.ea.validators.LoginRequestValidator.validateLoginRequest;


@Api("Auth API")
@Path("/api/auth")
public class AuthController {
    private final ObjectMapper objectMapper;
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.objectMapper = new ObjectMapper();
        this.authService = authService;
    }

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(LoginRequest loginRequest) {
        // Validate the loginRequest object data here before going any further.
        if (!validateLoginRequest(loginRequest)) {
            return Response.status(Response.Status.BAD_REQUEST).entity("Invalid Login Details.").build();
        }

        try {
            String jwtToken = authService.login(loginRequest);
            return Response.ok().entity(jwtToken).build();
        } catch (SQLException | DatabaseConnectionException e) {
            return Response.serverError().build();
        } catch (LoginException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }
}
