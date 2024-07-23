package com.kainos.ea.service;

import com.kainos.ea.daos.AuthDao;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.LoginException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.models.User;
import com.kainos.ea.services.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.security.Key;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class AuthServiceTest {
    private static final User USER = new User("user1@email.com", "validP4$Sword!123");
    private static final LoginRequest LOGIN_REQUEST = new LoginRequest("user1@email.com", "validP4$Sword!123");
    AuthDao mockDao = Mockito.mock(AuthDao.class);
    Key mockKey = Mockito.mock(Key.class);
    AuthService authService = new AuthService(mockDao, mockKey);

    @Test
    public void login_shouldThrowInvalidException_whenNoValidUserFound() throws DatabaseConnectionException, SQLException {
        when(mockDao.getUser(LOGIN_REQUEST)).thenReturn(null);

        LoginException exception = assertThrows(LoginException.class, () -> {
            authService.login(LOGIN_REQUEST);
        });

        String expectedExceptionMessage = "Login Request: Invalid Login Credentials!";
        String actualExceptionMessage = exception.getMessage();
        assertEquals(expectedExceptionMessage, actualExceptionMessage);
    }

    @Test
    public void login_shouldReturnJwtToken_whenValidUserFound() throws DatabaseConnectionException, SQLException {
        when(mockDao.getUser(LOGIN_REQUEST)).thenReturn(USER);


    }
}
