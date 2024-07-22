package com.kainos.ea.services;

import com.kainos.ea.daos.AuthDao;
import com.kainos.ea.exceptions.DatabaseConnectionException;
import com.kainos.ea.exceptions.Entity;
import com.kainos.ea.exceptions.InvalidException;
import com.kainos.ea.models.LoginRequest;
import com.kainos.ea.models.User;
import io.jsonwebtoken.Jwts;

import java.sql.SQLException;
import java.util.Date;

public class AuthService {
    private AuthDao authDao;

    public AuthService(AuthDao authDao) {
        this.authDao = authDao;
    }

    public String login(LoginRequest loginRequest) throws SQLException, DatabaseConnectionException, InvalidException {
        User user = authDao.getUser(loginRequest);

        if (user == null) {
            throw new InvalidException(Entity.USER);
        }
        return generateJwtToken(user);
    }

    private String generateJwtToken(User user) {
        return Jwts.builder().issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 28800000))
                .subject(user.getEmail())
                .issuer("DropwizardDemo")
                .signWith(Jwts.SIG.HS256.key().build())
                .compact();
    }
}
