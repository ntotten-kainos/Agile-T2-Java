package com.kainos.ea.controller;

import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.Entity;
import com.kainos.ea.exceptions.LoginException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.services.AuthService;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AuthControllerTest {
    AuthService mockAuthService = Mockito.mock(AuthService.class);
    AuthController authController = new AuthController(mockAuthService);
    private static final LoginRequest VALID_LOGIN_REQUEST = new LoginRequest(
            System.getenv().get("VALID_TEST_EMAIL"),
            System.getenv().get("VALID_TEST_PASSWORD")
    );

    private static final LoginRequest INVALID_LOGIN_REQUEST = new LoginRequest(
            "invalid.admin@email.com",
            "invalid"
    );

    @Test
    public void login_shouldReturnOK_whenValidLoginRequest() throws DatabaseConnectionException, SQLException, LoginException {
        when(mockAuthService.login(VALID_LOGIN_REQUEST)).thenReturn(
                Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 28800000))
                    .subject("admin@email.com")
                    .issuer("JobPortal_WebService")
                    .signWith(Jwts.SIG.HS256.key().build())
                    .compact()
        );

        Response response = authController.login(VALID_LOGIN_REQUEST);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity().toString());
    }

    @Test
    public void login_shouldReturnBadRequest_whenInvalidLogin() throws DatabaseConnectionException, SQLException, LoginException {
        when(mockAuthService.login(INVALID_LOGIN_REQUEST)).thenThrow(new LoginException(Entity.LOGIN_REQUEST));

        Response response = authController.login(INVALID_LOGIN_REQUEST);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
    }

    @Test
    public void login_shouldReturnServerError_whenSQLErrorOccurs() throws DatabaseConnectionException, SQLException, LoginException {
        when(mockAuthService.login(INVALID_LOGIN_REQUEST)).thenThrow(new SQLException());

        Response response = authController.login(INVALID_LOGIN_REQUEST);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

    @Test
    public void login_shouldReturnServerError_whenDBConnErrorOccurs() throws DatabaseConnectionException, SQLException, LoginException {
        when(mockAuthService.login(INVALID_LOGIN_REQUEST)).thenThrow(new DatabaseConnectionException(new Exception()));

        Response response = authController.login(INVALID_LOGIN_REQUEST);
        assertEquals(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());
    }

    @Test
    public void login_shouldReturnBadRequest_whenEmailIsInvalidFormat() throws DatabaseConnectionException, SQLException, LoginException {
        LoginRequest invalidEmailFormat = new LoginRequest(
                "invalidemail",
                "val1d!butN0t"
        );

        when(mockAuthService.login(invalidEmailFormat)).thenThrow(new LoginException(Entity.LOGIN_REQUEST));
        Response response = authController.login(invalidEmailFormat);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("Login Request: Invalid Login Credentials!", response.getEntity().toString());
    }

    @Test
    public void login_shouldReturnBadRequest_whenPasswordIsInvalidFormat() throws DatabaseConnectionException, SQLException, LoginException {
        LoginRequest invalidPasswordFormat = new LoginRequest(
                "valid.email@gmail.com",
                "invalid"
        );
        when(mockAuthService.login(invalidPasswordFormat)).thenThrow(new LoginException(Entity.LOGIN_REQUEST));
        Response response = authController.login(invalidPasswordFormat);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("Login Request: Invalid Login Credentials!", response.getEntity().toString());
    }
}
