package com.kainos.ea.services;

import com.kainos.ea.daos.AuthDao;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.Entity;
import com.kainos.ea.exceptions.LoginException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.models.User;
import io.jsonwebtoken.Jwts;

import javax.ws.rs.core.Response;
import java.security.Key;
import java.sql.SQLException;
import java.util.Date;

import static com.kainos.ea.validators.LoginRequestValidator.validateLoginRequest;

public class AuthService {
    private final AuthDao authDao;
    private final Key jwtKey;

    public AuthService(AuthDao authDao) {
        this.authDao = authDao;
        this.jwtKey = Jwts.SIG.HS256.key().build();
    }

    public String login(LoginRequest loginRequest) throws SQLException, DatabaseConnectionException, LoginException {
        // Validate the loginRequest object data here before going any further.
        if (!validateLoginRequest(loginRequest)) {
            throw new LoginException(Entity.LOGIN_REQUEST);
        }
        User user = authDao.getUser(loginRequest);

        if (user == null) {
            throw new LoginException(Entity.LOGIN_REQUEST);
        }
        return generateJwtToken(user);
    }

    private String generateJwtToken(User user) {
        return Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 28800000))
                .subject(user.getEmail())
                .issuer("JobPortal_WebService")
                .signWith(jwtKey)
                .compact();
    }
}
