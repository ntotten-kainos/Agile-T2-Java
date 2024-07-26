package com.kainos.ea.controllers;

import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.LoginException;
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
    /**
     * The Authentication Service.
     */
    private final AuthService authService;


    /**
     * Authentication Controller Constructor.
     * @param authServiceParam
     */
    public AuthController(final AuthService authServiceParam) {
        this.authService = authServiceParam;
    }

    /**
     * POST Request to authenticate a user using a LoginRequest object.
     * @param loginRequest contains the user credentials.
     * @return a response body with either a JWT Token or appropriate error.
     */
    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(final LoginRequest loginRequest) {
        try {
            return Response.ok()
                    .entity(authService.login(loginRequest))
                    .build();
        } catch (SQLException | DatabaseConnectionException e) {
            return Response.serverError().build();
        } catch (LoginException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
