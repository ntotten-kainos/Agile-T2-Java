package com.kainos.ea.services;

import com.kainos.ea.daos.AuthDao;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.Entity;
import com.kainos.ea.exceptions.LoginException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.models.User;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.sql.SQLException;
import java.util.Date;

public class AuthService {
    private AuthDao authDao;
    private Key jwtKey;

    public AuthService(AuthDao authDao, Key key) {
        this.authDao = authDao;
        this.jwtKey = key;
    }

    public String login(LoginRequest loginRequest) throws SQLException, DatabaseConnectionException, LoginException {
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
