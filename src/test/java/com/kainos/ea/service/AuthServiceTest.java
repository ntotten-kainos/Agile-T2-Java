package com.kainos.ea.service;

import com.kainos.ea.daos.AuthDao;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.LoginException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.models.User;
import com.kainos.ea.services.AuthService;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.Key;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class AuthServiceTest {
    private static final User USER = new User("user2@email.com", "validP4$Sword!123", 1);
    private static final LoginRequest LOGIN_REQUEST = new LoginRequest("user2@email.com", "validP4$Sword!123");
    AuthDao mockAuthDao = Mockito.mock(AuthDao.class);
    private static final Key jwtKey = Keys.secretKeyFor(SignatureAlgorithm.HS256); // new line?

    AuthService authService = new AuthService(mockAuthDao, jwtKey);

    @Test
    public void login_shouldThrowInvalidException_whenNoValidUserFound() throws DatabaseConnectionException, SQLException {
        when(mockAuthDao.getUser(LOGIN_REQUEST)).thenReturn(null);

        LoginException exception = assertThrows(LoginException.class, () -> {
            authService.login(LOGIN_REQUEST);
        });

        String expectedExceptionMessage = "Login Request: Invalid Login Credentials!";
        String actualExceptionMessage = exception.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
        verify(mockAuthDao, times(1)).getUser(LOGIN_REQUEST);
    }

    @Test
    public void login_shouldReturnJwtToken_whenValidUserFound() throws DatabaseConnectionException, SQLException, LoginException {
        when(mockAuthDao.getUser(LOGIN_REQUEST)).thenReturn(USER);
        assertNotNull(authService.login(LOGIN_REQUEST));
        verify(mockAuthDao, times(1)).getUser(LOGIN_REQUEST);
    }

    @Test
    public void login_shouldThrowDBConnException_whenDaoThrowsDBConnException() throws DatabaseConnectionException, SQLException {
        when(mockAuthDao.getUser(LOGIN_REQUEST)).thenThrow(new DatabaseConnectionException(new Exception("Connection Error")));
        assertThrows(DatabaseConnectionException.class, () -> authService.login(LOGIN_REQUEST));
        verify(mockAuthDao, times(1)).getUser(LOGIN_REQUEST);
    }

    @Test
    public void login_shouldThrowSqlException_whenDaoThrowsSqlException() throws DatabaseConnectionException, SQLException {
        when(mockAuthDao.getUser(LOGIN_REQUEST)).thenThrow(new SQLException());
        assertThrows(SQLException.class, () -> authService.login(LOGIN_REQUEST));
        verify(mockAuthDao, times(1)).getUser(LOGIN_REQUEST);
    }
}
