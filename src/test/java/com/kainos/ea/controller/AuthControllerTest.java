package com.kainos.ea.controller;

import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.Entity;
import com.kainos.ea.exceptions.InvalidException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.services.AuthService;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class AuthControllerTest {
    AuthService mockAuthService = Mockito.mock(AuthService.class);
    AuthController authController = new AuthController(mockAuthService);
    private final LoginRequest loginRequest = new LoginRequest(
            "admin@email.com",
            "admin"
    );

    @Test
    public void login_shouldReturnOK_whenValidLoginRequest() throws DatabaseConnectionException, SQLException, InvalidException {
        when(mockAuthService.login(loginRequest)).thenReturn(
                Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 28800000))
                    .subject("admin@email.com")
                    .issuer("JobPortal_WebService")
                    .signWith(Jwts.SIG.HS256.key().build())
                    .compact()
        );

        Response response = authController.login(loginRequest);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    public void login_shouldReturnBadRequest_whenInvalidLogin() throws DatabaseConnectionException, SQLException, InvalidException {
        when(mockAuthService.login(loginRequest)).thenThrow(new InvalidException(Entity.USER));

        Response response = authController.login(loginRequest);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void login_shouldReturnServerError_whenSQLErrorOccurs() throws DatabaseConnectionException, SQLException, InvalidException {
        when(mockAuthService.login(loginRequest)).thenThrow(new SQLException());

        Response response = authController.login(loginRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

    @Test
    public void login_shouldReturnServerError_whenDBConnErrorOccurs() throws DatabaseConnectionException, SQLException, InvalidException {
        when(mockAuthService.login(loginRequest)).thenThrow(new DatabaseConnectionException(new Exception()));

        Response response = authController.login(loginRequest);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

    public void login_shouldReturnBadRequest_whenEmailIsInvalidFormat() {
        LoginRequest invalidLogin = new LoginRequest(
                "testemail",
                ""
        );
    }
}
